package com.store.checkout.client.controllers;

import com.store.checkout.client.dtos.BasketRequest;
import com.store.checkout.client.dtos.BasketResponse;
import com.store.checkout.client.dtos.OrderRequest;
import com.store.checkout.client.services.BasketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/client/baskets")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping(path = "/amounts/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public double getTotalAmount(@PathVariable("basketId") String basketId) {
        return basketService.getTotalAmount(basketId);
    }

    @PostMapping
    public ResponseEntity<BasketResponse> save(@RequestBody OrderRequest orderRequest) {
        BasketResponse basket = basketService.saveBasket(orderRequest);
        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/baskets")
                .buildAndExpand(basket.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(basket, headers, HttpStatus.CREATED);
    }

    @PostMapping(path = "/products")
    public ResponseEntity<BasketResponse> add(@RequestBody BasketRequest basketRequest) {
        BasketResponse basket = basketService.saveProduct(basketRequest);
        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/products")
                .buildAndExpand(basket.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(basket, headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("basketId") String basketId) {
       basketService.delete(basketId);
    }

}
