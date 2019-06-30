package com.store.checkout.client.services;

import com.store.checkout.client.dtos.BasketRequest;
import com.store.checkout.client.dtos.BasketResponse;
import com.store.checkout.client.dtos.OrderRequest;
import com.store.checkout.client.repositories.http.ApiBasketRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultBasketService implements BasketService {

    private final ApiBasketRepository apiBasketRepository;

    public DefaultBasketService(ApiBasketRepository apiBasketRepository) {
        this.apiBasketRepository = apiBasketRepository;
    }

    @Override
    public BasketResponse saveBasket(OrderRequest orderRequest) {
        return apiBasketRepository.addBasket(orderRequest);
    }

    @Override
    public double getTotalAmount(String basketId) {
        String amount = apiBasketRepository.getTotalAmount(basketId);
        return Double.parseDouble(amount);
    }

    @Override
    public void delete(String basketId) {
       apiBasketRepository.deleteBasket(basketId);
    }

    @Override
    public BasketResponse saveProduct(BasketRequest basketRequest) {
        return apiBasketRepository.addProduct(basketRequest);
    }
}
