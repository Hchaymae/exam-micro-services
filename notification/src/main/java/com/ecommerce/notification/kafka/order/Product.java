package com.ecommerce.notification.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String Description,
        BigDecimal price,
        double quantity
) {
}
