package com.project.pcf_market.controller;

import com.project.pcf_market.dto.influencer.InfluencerDetailDTO;
import com.project.pcf_market.dto.influencer.InfluencerListDTO;
import com.project.pcf_market.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class InfluencerController {

    @Autowired
    InfluencerService influencerService;

    // 인플루언서 목록 조회
    @GetMapping("/")
    public List<InfluencerListDTO> getInfluencerList(){
        return influencerService.getInfluencerList();
    }

    @GetMapping("/{name}")
    public ResponseEntity<InfluencerDetailDTO> getInfluencerDetail(@PathVariable String name){
        InfluencerDetailDTO dto = influencerService.getInfluencerDetail(name);
        return ResponseEntity.ok(dto);
    }
}
