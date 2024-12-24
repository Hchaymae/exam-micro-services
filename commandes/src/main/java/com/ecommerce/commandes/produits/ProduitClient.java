package com.ecommerce.order.product;

import com.ecommerce.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProduitClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity =
                new HttpEntity<>(requestBody, headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {
                };

        ResponseEntity<List<PurchaseResponse>> responseEntity
                = restTemplate.exchange(
                productUrl + "/purchase",
                org.springframework.http.HttpMethod.POST,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()) {
            throw new BusinessException("Error while purchasing products");
        }
        return responseEntity.getBody();
    }
}