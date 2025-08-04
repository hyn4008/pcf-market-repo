package com.project.pcf_market.dto.influencer;

import com.project.pcf_market.domain.Field;
import com.project.pcf_market.dto.achievement.AchievementDTO;
import com.project.pcf_market.dto.product.ProductDTO;
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
}
