package com.store.checkout.client.services;

import com.store.checkout.client.dtos.BasketRequest;
import com.store.checkout.client.dtos.BasketResponse;
import com.store.checkout.client.dtos.OrderRequest;

public interface BasketService {

    BasketResponse saveBasket(OrderRequest orderRequest);
    double getTotalAmount(String basketId);
    void delete(String basketId);
    BasketResponse saveProduct(BasketRequest basketRequest);

}
