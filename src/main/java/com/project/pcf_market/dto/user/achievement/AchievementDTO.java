package com.project.pcf_market.dto.user.achievement;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class AchievementDTO {

    private Long id;
    private String title;
    private String detail;
    private LocalDateTime achievedAt;
}
