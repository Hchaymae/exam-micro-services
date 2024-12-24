package com.ecommerce.commandes;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandeResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final CommandeResolver orderService;

    public List<CommandeResponse> findAllOrders() {
        return orderService.findAllOrders();
    }

    public CommandeResponse findOrderById(Integer id) {
        return orderService.findById(id);
    }

    public Integer createOrder(CommandeRequest request) {
        CommandeRequest orderRequest = new CommandeRequest(
                request.getCustomerId(),
                request.getAmount(),
                request.getPaymentMethod(),
                request.getReference()
        );
        return orderService.createOrder(orderRequest);
    }
}