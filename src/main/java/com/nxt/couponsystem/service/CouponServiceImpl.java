package com.nxt.couponsystem.service;

import com.nxt.couponsystem.data.Coupon;
import com.nxt.couponsystem.enumeration.DiscountType;
import com.nxt.couponsystem.exception.CouponNotFoundException;
import com.nxt.couponsystem.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon applyCoupon(String couponCode, BigDecimal cartTotalPrice, Long itemCount) {
        validateCouponCode(couponCode);
        Coupon coupon = findValidCoupon(couponCode, cartTotalPrice, itemCount);
        calculateDiscountAndAdjustPrice(coupon, cartTotalPrice);
        return coupon;
    }

    private void validateCouponCode(String couponCode) {
        if (couponCode == null || couponCode.isEmpty()) {
            throw new IllegalArgumentException("Coupon code cannot be empty");
        }
    }

    private Coupon findValidCoupon(String couponCode, BigDecimal cartTotalPrice, Long itemCount) {
        Coupon coupon = couponRepository.findByCode(couponCode);
        if (coupon == null || !isValidCoupon(coupon, cartTotalPrice)) {
            throw new CouponNotFoundException("Coupon not found or invalid");
        }
        return coupon;
    }



    private boolean isValidCoupon(Coupon coupon, BigDecimal cartTotalPrice) {
        return cartTotalPrice.compareTo(coupon.getMinimumCartTotal()) >= 0;
    }

    private void calculateDiscountAndAdjustPrice(Coupon coupon, BigDecimal cartTotalPrice) {
        BigDecimal discountAmount = calculateDiscountAmount(coupon, cartTotalPrice);
        BigDecimal adjustedPrice = calculateAdjustedPrice(cartTotalPrice, discountAmount, coupon.getDiscountType());
        coupon.setDiscountAmount(adjustedPrice);
    }

    private BigDecimal calculateDiscountAmount(Coupon coupon, BigDecimal cartTotalPrice) {
        switch (coupon.getDiscountType()) {
            case FIXED_AMOUNT:
                return coupon.getDiscountAmount();
            case PERCENTAGE:
                BigDecimal discountPercentage = coupon.getDiscountAmount().divide(BigDecimal.valueOf(100));
                return cartTotalPrice.multiply(discountPercentage);
            default:
                throw new IllegalArgumentException("Invalid discount type: " + coupon.getDiscountType());
        }
    }

    private BigDecimal calculateAdjustedPrice(BigDecimal cartTotalPrice, BigDecimal discountAmount, DiscountType discountType) {
        BigDecimal adjustedPrice = cartTotalPrice;
        switch (discountType) {
            case FIXED_AMOUNT:
                return adjustedPrice.subtract(discountAmount);
            case PERCENTAGE:
                BigDecimal discountValue = cartTotalPrice.multiply(discountAmount.divide(BigDecimal.valueOf(100)));
                return adjustedPrice.subtract(discountValue);
            default:
                throw new IllegalArgumentException("Invalid discount type: " + discountType);
        }
    }
}
