package com.project.pcf_market.dto.admin.product;

import com.project.pcf_market.domain.Category;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateProductRequestDTO {

    private Long id;
    private String name;
    private String brand;
    private String thumbnail;
    private Category category;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private BigDecimal rating;
    private String purchaseUrl;
}
