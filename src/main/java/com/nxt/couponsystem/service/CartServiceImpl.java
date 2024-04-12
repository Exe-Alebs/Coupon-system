package com.nxt.couponsystem.service;

import com.nxt.couponsystem.data.Cart;
import com.nxt.couponsystem.data.Item;
import com.nxt.couponsystem.dto.CartDTO;
import com.nxt.couponsystem.dto.ItemDTO;
import com.nxt.couponsystem.repository.CartRepository;
import com.nxt.couponsystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    public CartDTO getCartDetails() {
        Cart cart = cartRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Cart not found"));
        List<Item> items = itemRepository.findByCartId(cart.getId());
        BigDecimal totalPrice = calculateTotalPrice(items);
        List<ItemDTO> itemDTOs = items.stream()
                .map(item -> new ItemDTO(item.getName(), item.getPrice()))
                .collect(Collectors.toList());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalPrice(totalPrice);
        cartDTO.setItems(itemDTOs);

        return cartDTO;
    }


    private BigDecimal calculateTotalPrice(List<Item> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        return totalPrice;
    }


    public long getItemCount() {
        return itemRepository.count();
    }

    public long getItemCountByCartId(Long cartId) {
        List<Item> items = itemRepository.findByCartId(cartId);
        return items.size();
    }
}
