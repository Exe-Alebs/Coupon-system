package com.nxt.couponsystem.seeder;

import com.nxt.couponsystem.data.Cart;
import com.nxt.couponsystem.data.Coupon;
import com.nxt.couponsystem.data.Item;
import com.nxt.couponsystem.dto.ItemDTO;
import com.nxt.couponsystem.enumeration.DiscountType;
import com.nxt.couponsystem.repository.CartRepository;
import com.nxt.couponsystem.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CartRepository cartRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public DataSeeder(CartRepository cartRepository, CouponRepository couponRepository) {
        this.cartRepository = cartRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCarts();
        seedCoupons();

    }

    List<ItemDTO> defaultItems = Arrays.asList(
            new ItemDTO("Phone", new BigDecimal("20.00")),
            new ItemDTO("Garri", new BigDecimal("20.00")),
            new ItemDTO("Eba", new BigDecimal("15.00")),
            new ItemDTO("Laptop", new BigDecimal("10.00")),
            new ItemDTO("Powerbank", new BigDecimal("20.00")),
            new ItemDTO("Charger", new BigDecimal("15.00"))
    );

    private BigDecimal calculateTotalPrice(List<ItemDTO> items){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ItemDTO item : items){
            totalPrice = totalPrice.add(item.getPrice());
        }
        return totalPrice;
    }
    public void seedCarts() {
        Cart cart1 = new Cart();
        cart1.setId(1L);
        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : defaultItems) {
            Item item = new Item();
            item.setName(itemDTO.getName());
            item.setPrice(itemDTO.getPrice());
            item.setCart(cart1);
            items.add(item);
        }
        cart1.setItems(items);
        cart1.setTotalPrice(calculateTotalPrice(defaultItems));
        cartRepository.save(cart1);
    }

    private void seedCoupons() {

        Coupon couponFixed10 = new Coupon();
        couponFixed10.setCode("FIXED10");
        couponFixed10.setDescription("Fixed $10 off total");
        couponFixed10.setMinimumCartTotal(new BigDecimal("50"));
        couponFixed10.setMinimumItemCount(1);
        couponFixed10.setDiscountType(DiscountType.FIXED_AMOUNT);
        couponFixed10.setDiscountAmount(new BigDecimal("10")); // Set discount amount
        couponFixed10.setTotalAdjustedPrice(new BigDecimal("40")); // Set total adjusted price
        couponRepository.save(couponFixed10);

        Coupon couponPercent10 = new Coupon();
        couponPercent10.setCode("PERCENT10");
        couponPercent10.setDescription("10% off total");
        couponPercent10.setMinimumCartTotal(new BigDecimal("100"));
        couponPercent10.setMinimumItemCount(2);
        couponPercent10.setDiscountType(DiscountType.PERCENTAGE);
        couponPercent10.setDiscountAmount(new BigDecimal("10")); // Set discount amount
        couponPercent10.setTotalAdjustedPrice(new BigDecimal("90")); // Set total adjusted price
        couponRepository.save(couponPercent10);

        Coupon couponMixed10 = new Coupon();
        couponMixed10.setCode("MIXED10");
        couponMixed10.setDescription("10% or $10 off total, whichever is greatest");
        couponMixed10.setMinimumCartTotal(new BigDecimal("200"));
        couponMixed10.setMinimumItemCount(3);
        couponMixed10.setDiscountType(DiscountType.MIXED);
        couponMixed10.setDiscountAmount(new BigDecimal("10")); // Set discount amount
        couponMixed10.setTotalAdjustedPrice(new BigDecimal("190")); // Set total adjusted price
        couponRepository.save(couponMixed10);

        Coupon couponRejected10 = new Coupon();
        couponRejected10.setCode("REJECTED10");
        couponRejected10.setDescription("$10 off total or 10% off total, whichever is greatest");
        couponRejected10.setMinimumCartTotal(new BigDecimal("1000"));
        couponRejected10.setDiscountType(DiscountType.MIXED);
        couponRejected10.setDiscountAmount(new BigDecimal("10")); // Set discount amount
        couponRejected10.setTotalAdjustedPrice(new BigDecimal("990")); // Set total adjusted price
        couponRepository.save(couponRejected10);

    }
}