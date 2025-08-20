package com.project.pcf_market.service.admin;

import com.project.pcf_market.dto.admin.product.AdminProductDTO;
import com.project.pcf_market.entity.Product;
import com.project.pcf_market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminProductService {

    @Autowired
    ProductRepository productRepository;

    // 상품 목록 조회
    public List<AdminProductDTO> getProductList() {
        return productRepository.findAll()
                .stream()
                .map(AdminProductDTO::new)
                .collect(Collectors.toList());
    }

    // 개별 상품 조회
    public AdminProductDTO getProductDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Product not found")));

        return new AdminProductDTO(product);
    }
}
