package com.store.checkout.client.services;

import com.store.checkout.client.dtos.BasketProduct;
import com.store.checkout.client.dtos.BasketRequest;
import com.store.checkout.client.dtos.BasketResponse;
import com.store.checkout.client.dtos.OrderRequest;
import com.store.checkout.client.repositories.http.ApiBasketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @InjectMocks
    private DefaultBasketService basketService;

    @Mock
    private ApiBasketRepository apiBasketRepository;

    @Test
    public void whenSaveBasketReturnsBasketResponse() {
        BasketResponse basketResponse = buildBasketResponse();

        when(apiBasketRepository.addBasket(any(OrderRequest.class))).thenReturn(basketResponse);

        BasketResponse result = basketService.saveBasket(new OrderRequest());

        assertThat(result.getId(), is(basketResponse.getId()));
        assertThat(result.getDateCreated(), is(basketResponse.getDateCreated()));
        assertThat(result.getStatus(), is(basketResponse.getStatus()));
        assertThat(result.getTotalAmount(), is(basketResponse.getTotalAmount()));
        assertThat(result.getBasketProducts().get(0).getQuantity(), is(basketResponse.getBasketProducts().get(0).getQuantity()));
    }

    @Test
    public void whenGetTotalAmountReturnsDouble() {
        when(apiBasketRepository.getTotalAmount(anyString())).thenReturn("10.5");

        double result = basketService.getTotalAmount("");

        assertThat(result, is(10.5));
    }

    @Test
    public void whenDeleteCallsRepository() {
        basketService.delete("1L");

        verify(apiBasketRepository, times(1)).deleteBasket(anyString());
    }

    @Test
    public void whenSaveProductReturnsBasketResponse() {
        BasketResponse basketResponse = buildBasketResponse();

        when(apiBasketRepository.addProduct(any(BasketRequest.class))).thenReturn(basketResponse);

        BasketResponse result = basketService.saveProduct(new BasketRequest());

        assertThat(result.getId(), is(basketResponse.getId()));
        assertThat(result.getDateCreated(), is(basketResponse.getDateCreated()));
        assertThat(result.getStatus(), is(basketResponse.getStatus()));
        assertThat(result.getTotalAmount(), is(basketResponse.getTotalAmount()));
        assertThat(result.getBasketProducts().get(0).getQuantity(), is(basketResponse.getBasketProducts().get(0).getQuantity()));
    }

    private BasketResponse buildBasketResponse() {
        List<BasketProduct> basketProducts = new ArrayList<>();
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setQuantity(new Integer(2));
        basketProducts.add(basketProduct);

        return BasketResponse.builder()
                .id(1L)
                .dateCreated("30/06/2019")
                .status("PAID")
                .totalAmount(15.5)
                .basketProducts(basketProducts)
                .build();
    }
    
}