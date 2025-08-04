package com.project.pcf_market.service;

import com.project.pcf_market.dto.influencer.InfluencerListDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.repository.InfluencerRepository;
import com.project.pcf_market.dto.achievement.AchievementDTO;
import com.project.pcf_market.dto.influencer.InfluencerDetailDTO;
import com.project.pcf_market.dto.product.ProductDTO;
import com.project.pcf_market.exception.NotFoundException;
import java.util.List;

@Service
public class InfluencerService {
    @Autowired
    InfluencerRepository influencerRepository;

    // 인플루언서 목록 조회
    public List<InfluencerListDTO> getInfluencerList(){
        return influencerRepository.findAllInfluencers();
    }

    // 특정 인플루언서 조회
    public InfluencerDetailDTO getInfluencerDetail(String name){
        Influencer influencer = influencerRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("인플루언서를 찾을 수 없습니다."));

        InfluencerDetailDTO dto = new InfluencerDetailDTO();

        // 인플루언서 프로필 정보
        dto.setId(influencer.getId());
        dto.setName(influencer.getName());
        dto.setField(influencer.getField());
        dto.setIntroduction(influencer.getIntroduction());
        dto.setProfileImage(influencer.getProfileImage());
        dto.setSubscriber(influencer.getSubscriber());
        dto.setYoutube(influencer.getYoutube());
        dto.setInstagram(influencer.getInstagram());
        dto.setBlog(influencer.getBlog());
        dto.setInstagram(influencer.getInstagram());

        // 해당 인플루언서의 이력 목록
        dto.setAchievementList(
                influencer.getAchievementList().stream()
                        .map(a -> {
                            AchievementDTO adto = new AchievementDTO();

                            adto.setId(a.getId());
                            adto.setTitle(a.getTitle());
                            adto.setDetail(a.getDetail());
                            adto.setAchievedAt(a.getAchievedAt());

                            return adto;
                        }).toList()
        );

        // 해당 인플루언서의 추천 제품 목록
        dto.setProductList(
                influencer.getProductList().stream()
                        .map(p -> {
                            ProductDTO pdto = new ProductDTO();

                            pdto.setId(p.getId());
                            pdto.setName(p.getName());
                            pdto.setBrand(p.getBrand());
                            pdto.setThumbnail(p.getThumbnail());
                            pdto.setCategory(p.getCategory());
                            pdto.setOriginalPrice(p.getOriginalPrice());
                            pdto.setCurrentPrice(p.getCurrentPrice());
                            pdto.setRating(p.getRating());

                            return pdto;
                        }).toList()
        );

        return dto;
    }
}
