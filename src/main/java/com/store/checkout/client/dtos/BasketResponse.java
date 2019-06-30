package com.store.checkout.client.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketResponse {

    private long id;
    private String dateCreated;
    private String status;
    private List<BasketProduct> basketProducts;
    private double totalAmount;

}
