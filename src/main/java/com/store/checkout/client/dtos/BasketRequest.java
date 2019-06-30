package com.store.checkout.client.dtos;

import lombok.Data;

@Data
public class BasketRequest {
    private long basketId;
    private Product product;
    private Integer quantity;
}
