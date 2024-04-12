package com.nxt.couponsystem.data;

import com.nxt.couponsystem.enumeration.DiscountType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name= "description")
    private String description;

    @Column(name = "minimum_cart_total", nullable = false)
    private  BigDecimal minimumCartTotal;

    @Column(name = "minimum_item_count", nullable = false)
    private int minimumItemCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    @Column(name = "discount_amount" ,nullable = false)
    private BigDecimal discountAmount;

    @Column(name = "adjusted_price", nullable = false)
    private BigDecimal totalAdjustedPrice;


    public Coupon(String couponCode) {
    }
}
