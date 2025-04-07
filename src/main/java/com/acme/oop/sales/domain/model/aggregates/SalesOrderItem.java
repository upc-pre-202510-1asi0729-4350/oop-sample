package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;

/**
 * Represents an item in a sales order.
 * Each item has a product ID, quantity, and unit price.
 * It provides methods to calculate the subtotal for the item.
 * @author Open Source Application Development Team
 */
@Getter
public class SalesOrderItem {
    private final ProductId productId;
    private final int quantity;
    private final Money unitPrice;

    /**
     * Constructor for SalesOrderItem.
     *
     * @param productId the ID of the product, which must not be null
     * @param quantity  the quantity of the product, which must be greater than zero
     * @param unitPrice the unit price of the product, which must not be null
     * @throws IllegalArgumentException if quantity is less than or equal to zero, or if unitPrice or productId is null
     */
    public SalesOrderItem(ProductId productId, int quantity, Money unitPrice) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        if (unitPrice == null)
            throw new IllegalArgumentException("Unit price must not be null");
        if (productId == null)
            throw new IllegalArgumentException("Product ID must not be null");

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the subtotal for this sales order item.
     *
     * @return the subtotal for the item as a Money object
     */
    public Money calculateSubtotal() {
        return unitPrice.multiply(quantity);
    }

}
