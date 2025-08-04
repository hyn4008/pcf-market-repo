package com.project.pcf_market.entity;

import com.project.pcf_market.domain.Category;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product", schema = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(name = "original_price", nullable = false)
    private BigDecimal originalPrice;

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    private BigDecimal rating;
}