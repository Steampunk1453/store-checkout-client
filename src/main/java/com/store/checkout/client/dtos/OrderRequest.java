package com.store.checkout.client.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<BasketRequest> basket;
}
