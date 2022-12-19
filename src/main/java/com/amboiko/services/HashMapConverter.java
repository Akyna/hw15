package com.amboiko.services;

import com.amboiko.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<Product, Integer>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Product, Integer> customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            System.out.println(e);
        }

        return customerInfoJson;
    }

    @Override
    public Map<Product, Integer> convertToEntityAttribute(String customerInfoJSON) {

        Map<Product, Integer> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<HashMap<Product, Integer>>() {
                    });
        } catch (final IOException e) {
            System.out.println(e);
        }

        return customerInfo;
    }
}