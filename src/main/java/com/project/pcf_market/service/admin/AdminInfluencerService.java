package com.project.pcf_market.service.admin;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminInfluencerService {

    @Autowired
    InfluencerRepository influencerRepository;

    // 인플루언서 목록 조회
    public List<AdminInfluencerListDTO> getInfluencerList() { return influencerRepository.findAllInfluencersForAdmin(); }
}
