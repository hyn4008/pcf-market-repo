package com.project.pcf_market.service.admin;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.dto.admin.influencer.CreateInfluencerRequestDTO;
import com.project.pcf_market.entity.Achievement;
import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.entity.InfluencerProduct;
import com.project.pcf_market.entity.Product;
import com.project.pcf_market.repository.InfluencerRepository;
import com.project.pcf_market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminInfluencerService {

    @Autowired
    InfluencerRepository influencerRepository;

    @Autowired
    ProductRepository productRepository;

    // 인플루언서 목록 조회
    public List<AdminInfluencerListDTO> getInfluencerList() { return influencerRepository.findAllInfluencersForAdmin(); }

    // 인플루언서 등록
    public Influencer createInfluencer(CreateInfluencerRequestDTO request) {

        // 요청값을 통해 등록할 인플루언서 객체 생성 및 초기화
        // 입력값 없는 nullable 필드는 자동으로 null 저장
        Influencer influencer = Influencer.builder()
                .name(request.getName())
                .field(request.getField())
                .introduction(request.getIntroduction())
                .profileImage(request.getProfileImage())
                .subscriber(request.getSubscriber())
                .youtube(request.getYoutube())
                .instagram(request.getInstagram())
                .blog(request.getBlog())
                .email(request.getEmail())
                .build();

        // 생성된 인플루언서를 DB에 저장
        Influencer savedInfluencer = influencerRepository.save(influencer);

        // 저장된 인플루언서의 이력 정보를 저장할 리스트 객체 생성 및 초기화
        List<Achievement> achievementList = Optional.ofNullable(request.getAchievementList())
                .orElse(Collections.emptyList()).stream()
                .map(achievement -> Achievement.builder()
                        .influencer(savedInfluencer)  // DB에 저장된 influencer 사용
                        .title(achievement.getTitle())
                        .detail(achievement.getDetail())
                        .achievedAt(achievement.getAchievedAt())
                        .build())
                .collect(Collectors.toList());

        // 이력 정보를 인플루언서 객체에 저장
        savedInfluencer.setAchievementList(achievementList);

        // 저장된 인플루언서의 관련 제품을 저장할 리스트 객체 생성 및 초기화
        List<InfluencerProduct> relationList = request.getProductIdList().stream()
                .map(productId -> {
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    return InfluencerProduct.builder()
                            .influencer(influencer)
                            .product(product)
                            .build();
                })
                .collect(Collectors.toList());

        // 관련 제품을 인플루언서 객체에 저장
        savedInfluencer.setRelationList(relationList);

        // 해당 인플루언서의 이력 정보와 관련 제품을 DB에 저장
        return influencerRepository.save(savedInfluencer);
    }
}
