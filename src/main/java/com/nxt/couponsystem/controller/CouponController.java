package com.nxt.couponsystem.controller;

import com.nxt.couponsystem.data.Coupon;
import com.nxt.couponsystem.service.CartServiceImpl;
import com.nxt.couponsystem.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @PostMapping("/{couponCode}")
    public ResponseEntity<Coupon> applyCoupon(@PathVariable String couponCode) {
        BigDecimal totalPrice = cartServiceImpl.getCartDetails().getTotalPrice();
        Long itemCount = cartServiceImpl.getItemCount();
        Coupon coupon = couponService.applyCoupon(couponCode, totalPrice, itemCount);
        if (coupon != null) {
            return ResponseEntity.ok(coupon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
