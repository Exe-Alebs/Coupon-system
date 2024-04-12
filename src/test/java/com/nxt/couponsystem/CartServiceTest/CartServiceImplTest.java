package com.nxt.couponsystem.CartServiceTest;

import com.nxt.couponsystem.data.Cart;

import com.nxt.couponsystem.data.Item;
import com.nxt.couponsystem.dto.CartDTO;
import com.nxt.couponsystem.exception.CartNotFoundException;
import com.nxt.couponsystem.repository.CartRepository;
import com.nxt.couponsystem.repository.ItemRepository;
import com.nxt.couponsystem.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartServiceImplTest {
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CartServiceImpl cartService;



    @BeforeEach
    void setUp(){
        cartRepository = mock(CartRepository.class);
        itemRepository=  mock(ItemRepository.class);
        cartService = new CartServiceImpl(cartRepository, itemRepository);
    }

    @Test
    void  getCartDetails_ReturnsCartDTO(){
        Cart cart = new Cart();
        cart.setId(1L);
        List<Item> items = Arrays.asList(new Item("Item 1", BigDecimal.TEN), new Item("Item 2", BigDecimal.valueOf(15)));
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when((itemRepository.findByCartId(1L))).thenReturn(items);


        CartDTO cartDTO = cartService.getCartDetails();


        Assertions.assertThat(cartDTO).isNotNull();
        Assertions.assertThat(cartDTO.getItems().size()).isEqualTo(2);
        Assertions.assertThat(cartDTO.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(25));
    }
    @Test
    void getCartDetails_CartNotFound_ThrowsNoSuchElementException() {
        // Given
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        // Then
        assertThrows(NoSuchElementException.class, () -> cartService.getCartDetails());
    }

}

