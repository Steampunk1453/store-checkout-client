package com.store.checkout.client.repositories.http;

import com.store.checkout.client.dtos.BasketRequest;
import com.store.checkout.client.dtos.BasketResponse;
import com.store.checkout.client.dtos.OrderRequest;

public interface ApiBasketRepository {

    BasketResponse addBasket(OrderRequest orderRequest);
    String getTotalAmount(String basketId);
    BasketResponse addProduct(BasketRequest basketRequest);
    void deleteBasket(String basketId);

}
