package com.project.pcf_market.dto.user.influencer;

import com.project.pcf_market.domain.Field;
import com.project.pcf_market.dto.admin.achievement.AdminAchievementDTO;
import com.project.pcf_market.dto.admin.product.AdminProductDTO;
import com.project.pcf_market.dto.user.achievement.AchievementDTO;
import com.project.pcf_market.dto.user.product.ProductDTO;
import com.project.pcf_market.entity.Influencer;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class InfluencerDetailDTO {

    private Long id;
    private String name;
    private Field field;
    private String introduction;
    private String profileImage;
    private Integer subscriber;
    private String youtube;
    private String instagram;
    private String blog;
    private String email;
    private List<AchievementDTO> achievementList;
    private List<ProductDTO> productList;

    // influencer 객체를 통해 DTO를 초기화하는 생성자
    public InfluencerDetailDTO(Influencer influencer) {
        this.id = influencer.getId();
        this.name = influencer.getName();
        this.field = influencer.getField();
        this.introduction = influencer.getIntroduction();
        this.profileImage = influencer.getProfileImage();
        this.subscriber = influencer.getSubscriber();
        this.youtube = influencer.getYoutube();
        this.instagram = influencer.getInstagram();
        this.blog = influencer.getBlog();
        this.email = influencer.getEmail();
        this.achievementList = influencer.getAchievementList().stream()
                .map(achievement -> new AchievementDTO(achievement))
                .toList();
        this.productList = influencer.getRelationList().stream()
                .map(relation -> new ProductDTO(relation.getProduct()))
                .toList();
    }
}
