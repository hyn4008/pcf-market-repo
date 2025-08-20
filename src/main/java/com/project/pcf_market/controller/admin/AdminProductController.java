package com.project.pcf_market.controller.admin;

import com.project.pcf_market.dto.admin.product.AdminProductDTO;
import com.project.pcf_market.service.admin.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
