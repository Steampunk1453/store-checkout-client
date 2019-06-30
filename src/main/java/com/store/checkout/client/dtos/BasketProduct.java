package com.store.checkout.client.dtos;

import lombok.Data;

@Data
public class BasketProduct {
    private Integer quantity;
    private Product product;
}
