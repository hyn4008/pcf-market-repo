package com.project.pcf_market.repository;

import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.entity.InfluencerProduct;
import com.project.pcf_market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluencerProductRepository extends JpaRepository<InfluencerProduct, Long> {

    // influencerId를 전달하면 연결된 모든 Product 반환
    @Query("SELECT ip.product FROM InfluencerProduct ip WHERE ip.influencer.id = :influencerId")
    List<Product> findProductsByInfluencerId(@Param("influencerId") Long influencerId);

    // productId를 전달하면 연결된 모든 influencer 반환
    @Query("SELECT ip.influencer FROM InfluencerProduct ip WHERE ip.product.id = :productId")
    List<Influencer> findInfluencersByProductId(@Param("productId") Long productId);
}
