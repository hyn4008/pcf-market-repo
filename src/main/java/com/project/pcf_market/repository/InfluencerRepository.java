package com.project.pcf_market.repository;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.dto.user.influencer.InfluencerListDTO;
import com.project.pcf_market.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

    // user용
    @Query("SELECT new com.project.pcf_market.dto.user.influencer.InfluencerListDTO(" +
            "i.id, i.name, i.introduction, i.profileImage, i.subscriber) " +
            "FROM Influencer i")
    List<InfluencerListDTO> findAllInfluencers();

    Optional<Influencer> findByName(String name);

    // admin용
    @Query("SELECT new com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO(" +
            "i.id, i.name, i.field, i.subscriber, i.youtube, COUNT(p.id)) " +
            "FROM Influencer i JOIN i.relationList rl JOIN rl.product p " +
            "GROUP BY i.id, i.name, i.field, i.subscriber, i.youtube")
    List<AdminInfluencerListDTO> findAllInfluencersForAdmin();
}
