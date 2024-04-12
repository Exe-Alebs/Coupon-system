package com.nxt.couponsystem.mappers;

import com.nxt.couponsystem.data.Cart;
import com.nxt.couponsystem.dto.CartDTO;
import com.nxt.couponsystem.dto.ItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CartDTO toDTO(Cart cart){
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setItems(cart.getItems().stream().map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList()));
        return cartDTO;

    }


}
