package com.ecommerce.dto;

import com.ecommerce.enums.ProductStatus;

public record ProductDetailsDto(
        Long id,
        String name,
        Double price,
        int stockQuantity,
        ProductStatus status,
        String categoryName,
        String vendorName
) {
}
