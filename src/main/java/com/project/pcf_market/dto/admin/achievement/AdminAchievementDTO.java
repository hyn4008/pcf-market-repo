package com.project.pcf_market.dto.admin.achievement;

import com.project.pcf_market.entity.Achievement;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class AdminAchievementDTO {

    private Long id;
    private String title;
    private String detail;
    private LocalDateTime achievedAt;

    public AdminAchievementDTO(Achievement achievement){
        this.id = achievement.getId();
        this.title = achievement.getTitle();
        this.detail = achievement.getDetail();
        this.achievedAt = achievement.getAchievedAt();
    }
}
