package com.ecommerce.order.kafka;

import com.ecommerce.order.customer.CustomerResponse;
import com.ecommerce.order.PaymentMethod;
import com.ecommerce.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record CommandeConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
