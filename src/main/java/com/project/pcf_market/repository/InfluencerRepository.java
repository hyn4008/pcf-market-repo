package com.project.pcf_market.repository;

import com.project.pcf_market.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.Query;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
}
