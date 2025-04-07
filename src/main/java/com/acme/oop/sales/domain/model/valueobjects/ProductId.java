package com.acme.oop.sales.domain.model.valueobjects;

import java.util.UUID;

/**
 * Represents a unique identifier for a product within the sales bounded context.
 * This class is immutable and final, ensuring that the ProductId cannot be modified after creation.
 * It uses a UUID to represent the unique identifier.
 * @author Open Source Application Development Team
 */
public record ProductId(UUID value) {

    /**
     * Constructor for ProductId with a UUID value.
     *
     * @param value the UUID value of the ProductId
     * @throws IllegalArgumentException if the value is null
     */
    public ProductId {
        if (value == null)
            throw new IllegalArgumentException("ProductId cannot be null");
    }

    /**
     * Constructor for ProductId with a random UUID.
     */
    public ProductId() {
        this(UUID.randomUUID());
    }

}

