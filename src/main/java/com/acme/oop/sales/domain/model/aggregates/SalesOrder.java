package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the sales order aggregate root in the sales bounded context.
 * Each sales order is associated with a customer and contains a list of items.
 * It provides methods to add items and calculate the total amount for the order.
 * @author Open Source Application Development Team
 */
@Getter
public class SalesOrder {
    private final UUID id;
    private final CustomerId customerId;
    private final LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    private Money totalAmount;

    /**
     * Constructor for SalesOrder.
     * This constructor initializes a new sales order with a customer ID,
     * the current date and time, and an empty list of items.
     *
     * @param customerId the ID of the customer, which must not be null
     * @throws IllegalArgumentException if customerId is null
     */
    public SalesOrder(CustomerId customerId) {
        if (customerId == null)
            throw new IllegalArgumentException("Customer ID cannot be null");
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalAmount = Money.zero();
    }

    /**
     * Adds an item to the sales order.
     * @param productId the ID of the product, which must not be null
     * @param quantity the quantity of the product, which must be greater than zero
     * @param unitPrice the unit price of the product, which must not be null and greater than zero
     */
    public void addItem(ProductId productId, int quantity, Money unitPrice) {
        if (productId == null)
            throw new IllegalArgumentException("Product ID cannot be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        if (unitPrice == null || unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than zero");

        SalesOrderItem item = new SalesOrderItem(productId, quantity, unitPrice);
        items.add(item);
        totalAmount = calculateTotalAmount();
    }

    /**
     * Calculates the total amount for the sales order.
     *
     * @return the total amount as a Money object
     */
    public Money calculateTotalAmount() {
        return items.stream().map(SalesOrderItem::calculateSubtotal)
                .reduce(Money.zero(), Money::add);
    }
}
