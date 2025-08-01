package com.project.pcf_market.controller;

import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class InfluencerController {

    @Autowired
    InfluencerService influencerService;

    // 인플루언서 목록 조회
    @GetMapping("/list")
    public List<Influencer> getInfluencers(){
        return influencerService.getAllInfluencers();
    }
}
