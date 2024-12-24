package com.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class CommandeMapper {


  public Commande toOrder(CommandeRequest request) {
    if (request == null) {
      return null;
    }
    return Commande.builder()
        .id(request.id())
        .reference(request.reference())
        .paymentMethod(request.paymentMethod())
        .customerId(request.customerId())
        .build();
  }

  public CommandeResponse fromOrder(Commande order) {
    return new CommandeResponse(
        order.getId(),
        order.getReference(),
        order.getTotalAmount(),
        order.getPaymentMethod(),
        order.getCustomerId()
    );
  }
}
