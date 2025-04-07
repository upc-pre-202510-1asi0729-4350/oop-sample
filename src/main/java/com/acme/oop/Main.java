package com.acme.oop;

import com.acme.oop.crm.domain.model.aggregates.Customer;
import com.acme.oop.sales.domain.model.aggregates.SalesOrder;
import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.Money;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Main entry point for the application.
 * Demonstrates the usage of the CRM and Sales bounded contexts.
 *
 * @author Open Source Application Development Team
 */
public class Main {
    /**
     * Main method to run the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // CRM context
        Address address = new Address("123 Main St", "Springfield", "IL", "62704", "USA");
        Customer customer = new Customer("John Doe","john.doe@gmail.com", address);
        System.out.println("Customer Name: " + customer.getName());
        Address anotherAddress = new Address("456 Elm St", "Springfield", "IL", "62704", "USA");
        customer.updateContactInfo("john.doe@outlook.com", anotherAddress);
        System.out.println("Updated Email: " + customer.getEmail());

        // Sales Context
        SalesOrder order = new SalesOrder(customer.getId());
        Money price = new Money(new BigDecimal("29.99"),
                Currency.getInstance("USD"));
        ProductId productId = new ProductId();
        order.addItem(productId, 2, price);

        // Display results
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer ID: " + order.getCustomerId());
        System.out.println("Total Amount: " + order.getTotalAmount()
                + " " + order.getTotalAmount().currency());
    }
}