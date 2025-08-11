package com.project.pcf_market.controller.admin;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.service.admin.AdminInfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminInfluencerController {

    @Autowired
    AdminInfluencerService adminInfluencerService;

    // 인플루언서 목록 조회
    @GetMapping("/admin/influencer")
    public List<AdminInfluencerListDTO> getInfluencerList() { return adminInfluencerService.getInfluencerList(); }

    // 개별 인플루언서 조회

    // 인플루언서 등록

    // 인플루언서 삭제

    // 인플루언서 수정
}
