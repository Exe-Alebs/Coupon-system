package com.nxt.couponsystem.service;

import com.nxt.couponsystem.dto.CartDTO;

public interface CartService {
    CartDTO getCartDetails();
    long getItemCount();
    long getItemCountByCartId(Long cartId);

}
