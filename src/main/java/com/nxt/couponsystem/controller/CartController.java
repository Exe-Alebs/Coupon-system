package com.nxt.couponsystem.controller;

import com.nxt.couponsystem.dto.CartDTO;
import com.nxt.couponsystem.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping
    public ResponseEntity<CartDTO> getCart() {
        CartDTO cart = cartService.getCartDetails();
        return ResponseEntity.ok(cart);
    }
}
