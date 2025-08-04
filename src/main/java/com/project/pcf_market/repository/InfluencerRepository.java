package com.project.pcf_market.repository;

import com.project.pcf_market.dto.influencer.InfluencerListDTO;
import com.project.pcf_market.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

    @Query("SELECT new com.project.pcf_market.dto.influencer.InfluencerListDTO(i.id, i.name, i.introduction, i.profileImage, i.subscriber) FROM Influencer i")
    List<InfluencerListDTO> findAllInfluencers();

    Optional<Influencer> findByName(String name);
}
