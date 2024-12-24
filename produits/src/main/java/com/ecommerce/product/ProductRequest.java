package com.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
         int id,
         @NotNull(message="Product name is required")
         String name,

         @NotNull(message="Product description is required")
         String description,

         @Positive(message="Product quantity must be postive")
         double availableQuantity,

         @Positive(message="Product price must be postive")
         BigDecimal price,

         @NotNull(message="Product category is required")
         Integer categoryId
) {
}
