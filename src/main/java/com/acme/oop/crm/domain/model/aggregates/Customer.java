package com.acme.oop.crm.domain.model.aggregates;

import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a customer aggregate in the CRM bounded context.
 * This class serves as the aggregate root for the customer entity and contact information.
 *
 * @author Open Source Application Development Team
 */
@Getter
public class Customer {
    private final CustomerId id;
    @Setter private String name;
    @Setter private String email;
    @Setter private Address address;

    /**
     * Constructor for creating a new Customer instance.
     *
     * @param name    The name of the customer, must not be null or empty.
     * @param email   The email of the customer, must not be null or empty.
     * @param address The address of the customer, must not be null.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Customer(String name, String email, Address address) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");
        this.id = new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the customer's contact information.
     * @param email The new email of the customer, must not be null or empty.
     * @param address The new address of the customer, must not be null.
     * @throws IllegalArgumentException if any of the parameters are invalid.
     */
    public void updateContactInfo(String email, Address address) {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");
        this.email = email;
        this.address = address;
    }
}
