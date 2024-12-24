package com.ecommerce.commandes;

import com.ecommerce.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record CommandeRequest(
        Integer id,
        String reference,

        @Positive(message= "Amount must be positive")
        BigDecimal amount,

        @NotNull(message="Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message="Customer should present")
        @NotEmpty(message="Customer should present")
        @NotBlank(message="Customer should present")
        String customerId,

        @NotEmpty(message="You should at least have one product")
        List<PurchaseRequest> products
) {
}
