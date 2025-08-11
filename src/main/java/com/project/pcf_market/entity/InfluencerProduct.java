package com.project.pcf_market.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "influencer_product", schema = "customer")
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
