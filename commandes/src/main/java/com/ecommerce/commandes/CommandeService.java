package com.ecommerce.commandes;

import com.ecommerce.commandes.produits.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository repository;
    private final CommandeMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient produitsClient;
    private final CommandeLineService commandesLineService;
    private final CommandeProducer commandesProducer;

    @Transactional
    @CircuitBreaker(name = "produitsService", fallbackMethod = "fallbackCreateCommande")
    public Integer createCommande(CommandeRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create commandes:: No customer exists with the provided ID"));

        var purchasedProducts = produitsClient.purchaseProducts(request.produitss());

        var commandes = this.repository.save(mapper.toCommande(request));

        for (PurchaseRequest purchaseRequest : request.produitss()) {
            commandesLineService.saveCommandeLine(
                    new CommandeLineRequest(
                            null,
                            commandes.getId(),
                            purchaseRequest.produitsId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // Start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                commandes.getId(),
                commandes.getReference(),
                customer
        );
        paymentClient.requestCommandePayment(paymentRequest);

        commandesProducer.sendCommandeConfirmation(
                new CommandeConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return commandes.getId();
    }

    public Integer fallbackCreateCommande(CommandeRequest request, Throwable throwable) {
        // Fallback logic
        throw new BusinessException("Product service is currently unavailable. Please try again later.");
    }

    public List<CommandeResponse> findAllCommandes() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromCommande)
                .collect(Collectors.toList());
    }

    public CommandeResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(this.mapper::fromCommande)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No commandes found with the provided ID: %d", id)));
    }
}
