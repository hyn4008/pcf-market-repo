package com.project.pcf_market.controller.admin;

import com.project.pcf_market.dto.admin.product.AdminProductDTO;
import com.project.pcf_market.dto.admin.product.CreateProductRequestDTO;
import com.project.pcf_market.entity.Product;
import com.project.pcf_market.service.admin.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    // 상품 목록 조회
    @GetMapping("/admin/product")
    public ResponseEntity<List<AdminProductDTO>> getProductList() {
        return ResponseEntity.ok(adminProductService.getProductList());
    }

    // 개별 상품 조회
    @GetMapping("/admin/product/{id}")
    public ResponseEntity<AdminProductDTO> getProductDetail(@PathVariable Long id) {
        return ResponseEntity.ok(adminProductService.getProductDetail(id));
    }

    // 상품 등록
    @PostMapping("/admin/product")
    public ResponseEntity<AdminProductDTO> createProduct(@RequestBody CreateProductRequestDTO request) {
        Product createdProduct = adminProductService.createProduct(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AdminProductDTO(createdProduct));
    }
}
