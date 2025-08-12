package com.project.pcf_market.dto.admin.influencer;

import com.project.pcf_market.domain.Field;
import com.project.pcf_market.entity.Achievement;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateInfluencerRequestDTO {

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
    private List<Achievement> achievementList;
    private List<Long> productIdList;
}
