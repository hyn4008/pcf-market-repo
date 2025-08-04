package com.project.pcf_market.entity;

import com.project.pcf_market.domain.Field;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "influencer", schema = "customer")
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
    private Field field;

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

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Achievement> achievementList;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productList;
}