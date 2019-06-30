package com.store.checkout.client.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BasketResponse {

    private long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
    private String status;
    private List<BasketProduct> basketProducts;
    private double totalAmount;

}
