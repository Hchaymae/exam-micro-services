package com.ecommerce.commandes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class CommandeController {

    private final OrderResolver service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid CommandeRequest request
    ) {
        return ResponseEntity.ok(service.createOrder(request));
    }
}
