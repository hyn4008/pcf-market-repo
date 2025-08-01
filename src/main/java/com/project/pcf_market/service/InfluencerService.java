package com.project.pcf_market.service;

import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.repository.InfluencerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class InfluencerService {
    @Autowired
    InfluencerRepository influencerRepository;

    public List<Influencer> getAllInfluencers(){
        return influencerRepository.findAll();
    }
}
