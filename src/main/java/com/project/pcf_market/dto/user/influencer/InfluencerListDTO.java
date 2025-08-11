package com.project.pcf_market.dto.user.influencer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class InfluencerListDTO {

    private Long id;
    private String name;
    private String introduction;
    private String profileImage;
    private Integer subscriber;
}
