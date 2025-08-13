package com.project.pcf_market.service.user;

import com.project.pcf_market.dto.user.influencer.InfluencerListDTO;
import com.project.pcf_market.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.repository.InfluencerRepository;
import com.project.pcf_market.repository.InfluencerProductRepository;
import com.project.pcf_market.dto.user.achievement.AchievementDTO;
import com.project.pcf_market.dto.user.influencer.InfluencerDetailDTO;
import com.project.pcf_market.dto.user.product.ProductDTO;
import com.project.pcf_market.exception.NotFoundException;
import java.util.List;

@Service
public class InfluencerService {

    @Autowired
    InfluencerRepository influencerRepository;

    @Autowired
    InfluencerProductRepository influencerProductRepository;

    // 인플루언서 목록 조회
    public List<InfluencerListDTO> getInfluencerList(){
        return influencerRepository.findAllInfluencers();
    }

    // 개별 인플루언서 조회
    public InfluencerDetailDTO getInfluencerDetail(String name){
        // name을 통해 인플루언서 찾기
        Influencer influencer = influencerRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("influencer not found"));

        // 해당 인플루언서 객체를 통해 dto 생성 후 반환
        return new InfluencerDetailDTO(influencer);
    }
}
