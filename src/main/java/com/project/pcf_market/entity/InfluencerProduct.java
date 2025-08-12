package com.project.pcf_market.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "influencer_product", schema = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfluencerProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
