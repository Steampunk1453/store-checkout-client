package com.store.checkout.client.repositories.http;

import com.store.checkout.client.dtos.BasketRequest;
import com.store.checkout.client.dtos.BasketResponse;
import com.store.checkout.client.dtos.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class DefaultApiBasketRepository implements ApiBasketRepository {

    @Value("${url.store.checkout.service}")
    private String checkoutServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BasketResponse addBasket(OrderRequest orderRequest) {
        URI targetUrl = UriComponentsBuilder.fromUriString(checkoutServiceUrl)
                .path("/api/baskets")
                .build()
                .encode()
                .toUri();
        log.debug("URL endpoint: " + targetUrl);
        return restTemplate.exchange(targetUrl, HttpMethod.POST, new HttpEntity<>(orderRequest), BasketResponse.class)
                .getBody();
    }

    @Override
    public String getTotalAmount(String basketId) {
        String URL = checkoutServiceUrl + "api/baskets/amounts/{basketId}";
        Map<String, String> params = new HashMap<>();
        params.put("basketId", basketId);
        log.debug("URL endpoint: " + URL);
        return restTemplate.getForObject(URL, String.class, params);
    }

    @Override
    public BasketResponse addProduct(BasketRequest basketRequest) {
        URI targetUrl = UriComponentsBuilder.fromUriString(checkoutServiceUrl)
                .path("/api/baskets/products")
                .build()
                .encode()
                .toUri();
        log.debug("URL endpoint: " + targetUrl);
        return restTemplate.exchange(targetUrl, HttpMethod.POST, new HttpEntity<>(basketRequest), BasketResponse.class)
                .getBody();
    }

    @Override
    public void deleteBasket(String basketId) {
        String URL = checkoutServiceUrl + "api/baskets/{basketId}";
        Map<String, String> params = new HashMap<>();
        params.put("basketId", basketId);
        log.debug("URL endpoint: " + URL);
        restTemplate.delete(URL, params);
    }

}
