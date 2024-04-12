package com.nxt.couponsystem.service;

import com.nxt.couponsystem.data.Coupon;
import com.nxt.couponsystem.enumeration.DiscountType;
import com.nxt.couponsystem.exception.CouponNotFoundException;
import com.nxt.couponsystem.repository.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CouponServiceImplTest {
    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void applyCoupon_InvalidCouponCode_ThrowsCouponNotFoundException() {
        // Given
        String invalidCouponCode = "INVALIDCODE";
        BigDecimal cartTotalPrice = BigDecimal.valueOf(100);
        Long itemCount = 5L;
        when(couponRepository.findByCode(invalidCouponCode)).thenReturn(null);

        // Then
        assertThrows(CouponNotFoundException.class, () -> couponService.applyCoupon(invalidCouponCode, cartTotalPrice, itemCount));
    }


    @Test
    void applyCoupon_InsufficientCartTotalPrice_ThrowsCouponNotFoundException() {
        // Given
        String couponCode = "TESTCODE";
        BigDecimal cartTotalPrice = BigDecimal.valueOf(50);
        Long itemCount = 5L;
        Coupon coupon = new Coupon(couponCode, BigDecimal.valueOf(100), DiscountType.FIXED_AMOUNT, BigDecimal.valueOf(10), 3);
        when(couponRepository.findByCode(couponCode)).thenReturn(coupon);

        // Then
        assertThrows(CouponNotFoundException.class, () -> couponService.applyCoupon(couponCode, cartTotalPrice, itemCount));
    }


}
