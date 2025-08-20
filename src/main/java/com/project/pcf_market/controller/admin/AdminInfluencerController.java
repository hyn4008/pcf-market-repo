package com.project.pcf_market.controller.admin;

import com.project.pcf_market.dto.admin.influencer.AdminInfluencerDetailDTO;
import com.project.pcf_market.dto.admin.influencer.AdminInfluencerListDTO;
import com.project.pcf_market.dto.admin.influencer.CreateInfluencerRequestDTO;
import com.project.pcf_market.dto.admin.influencer.UpdateInfluencerRequestDTO;
import com.project.pcf_market.entity.Influencer;
import com.project.pcf_market.service.admin.AdminInfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminInfluencerController {

    @Autowired
    AdminInfluencerService adminInfluencerService;

    // 인플루언서 목록 조회
    @GetMapping("/admin/influencer")
    public ResponseEntity<List<AdminInfluencerListDTO>> getInfluencerList() {
        return ResponseEntity.ok(adminInfluencerService.getInfluencerList());
    }

    // 개별 인플루언서 조회
    @GetMapping("/admin/influencer/{id}")
    public ResponseEntity<AdminInfluencerDetailDTO> getInfluencerDetail(@PathVariable Long id) {
        return ResponseEntity.ok(adminInfluencerService.getInfluencerDetail(id));
    }

    // 인플루언서 등록
    @PostMapping("/admin/influencer")
    public ResponseEntity<AdminInfluencerDetailDTO> createInfluencer(@RequestBody CreateInfluencerRequestDTO request) {
        Influencer createdInfluencer = adminInfluencerService.createInfluencer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AdminInfluencerDetailDTO(createdInfluencer));
    }

    // 인플루언서 삭제 (Soft Delete)
    @DeleteMapping("/admin/influencer/{id}")
    public ResponseEntity<Void> deleteInfluencer(@PathVariable Long id) {
        adminInfluencerService.softDeleteInfluencer(id);
        return ResponseEntity.noContent().build();
    }

    // 인플루언서 수정
    @PutMapping("/admin/influencer/{id}")
    public ResponseEntity<AdminInfluencerDetailDTO> updateInfluencer(@RequestBody UpdateInfluencerRequestDTO request, @PathVariable Long id) {
        Influencer updatedInfluencer = adminInfluencerService.updateInfluencer(id, request);
        return ResponseEntity.ok(new AdminInfluencerDetailDTO(updatedInfluencer));
    }
}
