package com.project.pcf_market.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "influencer", schema = "influencer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Influencer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "field", nullable = false)
    private FieldType field;

    private String introduction;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(nullable = false)
    private Integer subscriber;

    @Column(nullable = false)
    private String youtube;

    private String instagram;

    private String blog;

    private String email;
}