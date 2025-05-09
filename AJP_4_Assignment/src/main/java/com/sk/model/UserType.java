package com.sk.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum UserType {
    ADMIN, CUSTOMER, STAFF;

    // Convert lowercase DB values to Enum
    public static UserType fromString(String value) {
        if (value == null) {
            return null;
        }
        return UserType.valueOf(value.toUpperCase());
    }
}

// Custom converter for Hibernate
@Converter(autoApply = true)
class UserTypeConverter implements AttributeConverter<UserType, String> {

    @Override
    public String convertToDatabaseColumn(UserType userType) {
        return (userType == null) ? null : userType.name().toLowerCase(); // Store in lowercase
    }

    @Override
    public UserType convertToEntityAttribute(String dbValue) {
        return (dbValue == null) ? null : UserType.fromString(dbValue); // Convert DB value to Enum
    }
}
