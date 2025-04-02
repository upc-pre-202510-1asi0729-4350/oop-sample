package com.acme.oop.shared.domain.model.valueobjects;

public record Address(String street, String number, String city, String postalCode, String country) {
    public Address {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Number cannot be null or empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
    }
}
