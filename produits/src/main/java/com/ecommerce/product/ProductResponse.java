package com.ecommerce.product;

import java.math.BigDecimal;

public record ProductResponse (
        int id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer CategoryId,
        String CategoryName,
        String CategoryDescription
) {
}
