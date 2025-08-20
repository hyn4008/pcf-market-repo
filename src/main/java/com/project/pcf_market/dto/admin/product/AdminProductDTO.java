package com.project.pcf_market.dto.admin.product;

import com.project.pcf_market.domain.Category;
import com.project.pcf_market.entity.Product;
import lombok.AllArgsConstructor;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AdminProductDTO {

    private Long id;
    private String name;
    private String brand;
    private String thumbnail;
    private Category category;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private BigDecimal rating;
    private String purchaseUrl;
    private Boolean isShow;

    public AdminProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.thumbnail = product.getThumbnail();
        this.category = product.getCategory();
        this.originalPrice = product.getOriginalPrice();
        this.currentPrice = product.getCurrentPrice();
        this.rating = product.getRating();
        this.purchaseUrl = product.getPurchaseUrl();
        this.isShow = product.isShow();
    }
}
