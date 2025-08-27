package com.project.pcf_market.service.admin;

import com.project.pcf_market.dto.admin.product.AdminProductDTO;
import com.project.pcf_market.dto.admin.product.CreateProductRequestDTO;
import com.project.pcf_market.dto.admin.product.UpdateProductRequestDTO;
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

    // 상품 등록
    public Product createProduct(CreateProductRequestDTO request) {

        // 요청값을 통해 등록할 상품 객체 생성 및 초기화
        Product product = Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .thumbnail(request.getThumbnail())
                .category(request.getCategory())
                .originalPrice(request.getOriginalPrice())
                .currentPrice(request.getCurrentPrice())
                .rating(request.getRating())
                .purchaseUrl(request.getPurchaseUrl())
                .build();

        // 해당 상품을 DB에 저장한 후 반환
        return productRepository.save(product);
    }

    // 상품 삭제 (Soft Delete)
    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Product not found")));
        product.setShow(false);
        productRepository.save(product);
    }

    // 상품 수정
    public Product updateProduct(Long id, UpdateProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Product not found")));

        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setThumbnail(request.getThumbnail());
        product.setCategory(request.getCategory());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setCurrentPrice(request.getCurrentPrice());
        product.setRating(request.getRating());
        product.setPurchaseUrl(request.getPurchaseUrl());
        product.setShow(request.getIsShow());

        return productRepository.save(product);
    }
}
