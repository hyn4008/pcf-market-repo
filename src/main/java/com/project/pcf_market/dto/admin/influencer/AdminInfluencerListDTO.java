package com.project.pcf_market.dto.admin.influencer;

import com.project.pcf_market.domain.Field;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AdminInfluencerListDTO {

    private Long id;
    private String name;
    private Field field;
    private Integer subscriber;
    private String youtube;
    private Long productCount; // 관련 상품 개수
}
