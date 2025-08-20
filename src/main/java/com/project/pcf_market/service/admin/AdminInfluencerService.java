package com.project.pcf_market.service.admin;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerDetailDTO;
import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.dto.admin.influencer.CreateInfluencerRequestDTO;
import com.project.pcf_market.dto.admin.influencer.UpdateInfluencerRequestDTO;
import com.project.pcf_market.entity.Achievement;
import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.entity.InfluencerProduct;
import com.project.pcf_market.entity.Product;
import com.project.pcf_market.repository.InfluencerRepository;
import com.project.pcf_market.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminInfluencerService {

    @Autowired
    InfluencerRepository influencerRepository;

    @Autowired
    ProductRepository productRepository;

    // 인플루언서 목록 조회
    public List<AdminInfluencerListDTO> getInfluencerList() {return influencerRepository.findAllInfluencersForAdmin(); }

    // 개별 인플루언서 조회
    public AdminInfluencerDetailDTO getInfluencerDetail(Long id) {
        // id를 통해 인플루언서 찾기
        Influencer influencer = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("influencer not found"));

        // 해당 인플루언서 객체를 통해 dto 생성 후 반환
        return new AdminInfluencerDetailDTO(influencer);
    }

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

        // 저장된 인플루언서의 관련 상품을 저장할 리스트 객체 생성 및 초기화
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

        // 관련 상품을 인플루언서 객체에 저장
        savedInfluencer.setRelationList(relationList);

        // 해당 인플루언서의 이력 정보와 관련 상품을 DB에 저장
        return influencerRepository.save(savedInfluencer);
    }

    // 인플루언서 삭제 (Soft Delete)
    public void softDeleteInfluencer(Long id) {
        Influencer influencer = influencerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Influencer not found"));
        influencer.setShow(false); // is_show: true -> false
        influencerRepository.save(influencer);
    }

    // 인플루언서 수정
    @Transactional
    public Influencer updateInfluencer(Long id, UpdateInfluencerRequestDTO request) {
        Influencer influencer = influencerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Influencer not found"));

        // 1. 기본 정보 업데이트
        influencer.setName(request.getName());
        influencer.setField(request.getField());
        influencer.setIntroduction(request.getIntroduction());
        influencer.setProfileImage(request.getProfileImage());
        influencer.setYoutube(request.getYoutube());
        influencer.setInstagram(request.getInstagram());
        influencer.setBlog(request.getBlog());
        influencer.setEmail(request.getEmail());
        influencer.setShow(request.getIsShow());


        // 2. Achievement 업데이트 (추가, 수정, 삭제)
        // 기존 Achievement 목록 불러오기
        Map<Long, Achievement> currentAchievementMap = Optional.ofNullable(influencer.getAchievementList())
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(Achievement::getId, achievement -> achievement));

        // 새로운 Achievement 목록 저장할 리스트 생성
        List<Achievement> newAchievementList = new ArrayList<>();
        if (request.getAchievementList() != null) {
            for (Achievement requestedAchievement : request.getAchievementList()) {
                if (requestedAchievement.getId() == null) {
                    // 2-1. 새로운 Achievement 추가 (반영 x)
                    requestedAchievement.setInfluencer(influencer);
                    newAchievementList.add(requestedAchievement);
                }
                else {
                    // 2-2. 기존 Achievement 수정
                    // id를 통해 Achievement 객체를 찾아 Map에서 제거 후 반환 (이후 Map에 존재하는 Achievement는 삭제 대상)
                    Achievement existingAchievement = currentAchievementMap.remove(requestedAchievement.getId());
                    if (existingAchievement != null) {
                        // 기존 Achievement 수정
                        existingAchievement.setInfluencer(influencer);
                        existingAchievement.setTitle(requestedAchievement.getTitle());
                        existingAchievement.setDetail(requestedAchievement.getDetail());
                        existingAchievement.setAchievedAt(requestedAchievement.getAchievedAt());
                        // 수정된 Achievement를 새로운 목록에 추가
                        newAchievementList.add(existingAchievement);
                    }
                    else {
                        throw new RuntimeException("Achievement not found");
                    }
                }
            }
        }

        // 2-3. Map에 남아있는 Achievement 삭제
        influencer.getAchievementList().removeIf(achievement -> currentAchievementMap.containsKey(achievement.getId()));

        // 2-1-2. 새로 추가된 Achievement 반영 (수정은 2-2에서 반영 완료)
        for (Achievement achievement : newAchievementList) {
            if (!influencer.getAchievementList().contains(achievement)) {
                influencer.getAchievementList().add(achievement);
            }
        }


        // 3. Relation 업데이트 (추가, 삭제)
        // Relation을 통해 기존 ProductId 목록 불러오기
        List<Long> currentProductIdList = Optional.ofNullable(influencer.getRelationList())
                .orElse(Collections.emptyList())
                .stream()
                .map(relation -> relation.getProduct().getId())
                .collect(Collectors.toList());

        // 요청을 통해 전달된 ProductId 목록 저장
        List<Long> newProductIdList = request.getProductIdList();

        // 3-1. List에 존재하지 않는 Relation 삭제
        influencer.getRelationList().removeIf(relation -> !newProductIdList.contains(relation.getProduct().getId()));

        // 3-2. 새로운 Relation 추가
        for (Long productId : newProductIdList) {
            if (!currentProductIdList.contains(productId)) {
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                InfluencerProduct influencerProduct = new InfluencerProduct().builder()
                        .influencer(influencer)
                        .product(product)
                        .build();
                influencer.getRelationList().add(influencerProduct);
            }
        }

        return influencerRepository.save(influencer);
    }
}
