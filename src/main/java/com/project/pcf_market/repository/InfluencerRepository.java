package com.project.pcf_market.repository;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerDetailDTO;
import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.dto.user.influencer.InfluencerListDTO;
import com.project.pcf_market.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

    // 사용자 페이지 내 인플루언서 목록 조회
    @Query("SELECT new com.project.pcf_market.dto.user.influencer.InfluencerListDTO(" +
            "i.id, i.name, i.introduction, i.profileImage, i.subscriber) " +
            "FROM Influencer i " +
            "WHERE i.isShow = true")
    List<InfluencerListDTO> findAllInfluencers();

    // 관리자 페이지 내 인플루언서 목록 조회
    @Query("SELECT new com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO(" +
            "i.id, i.name, i.field, i.subscriber, i.youtube, COUNT(p.id), i.isShow) " +
            "FROM Influencer i JOIN i.relationList rl JOIN rl.product p " +
            "GROUP BY i.id, i.name, i.field, i.subscriber, i.youtube")
    List<AdminInfluencerListDTO> findAllInfluencersForAdmin();

    // 이름을 통해 개별 인플루언서 조회
    Optional<Influencer> findByName(String name);
}
