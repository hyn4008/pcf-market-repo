package com.project.pcf_market.dto.admin.influencer;

import com.project.pcf_market.domain.Field;
import com.project.pcf_market.dto.admin.achievement.AdminAchievementDTO;
import com.project.pcf_market.dto.admin.product.AdminProductDTO;
import com.project.pcf_market.entity.Influencer;
import lombok.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class AdminInfluencerDetailDTO {

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
    private List<AdminAchievementDTO> achievementList;
    private List<AdminProductDTO> productList;

    // influencer 객체를 통해 DTO를 초기화하는 생성자
    public AdminInfluencerDetailDTO(Influencer influencer) {
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
                .map(achievement -> new AdminAchievementDTO(achievement))
                .toList();
        this.productList = influencer.getRelationList().stream()
                .map(relation -> new AdminProductDTO(relation.getProduct()))
                .toList();
    }
}
