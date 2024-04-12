package com.nxt.couponsystem.service;

import com.nxt.couponsystem.data.Coupon;

import java.math.BigDecimal;

public interface CouponService {
    Coupon applyCoupon(String couponCode, BigDecimal cartTotalPrice, Long itemCount);
}
