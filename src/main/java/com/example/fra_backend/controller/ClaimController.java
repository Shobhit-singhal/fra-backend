package com.example.fra_backend.controller;

import com.example.fra_backend.dto.ClaimRequestDto;
import com.example.fra_backend.dto.ClaimResponseDto;
import com.example.fra_backend.model.Claim;
import com.example.fra_backend.model.User;
import com.example.fra_backend.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<ClaimResponseDto> createClaim(@RequestBody ClaimRequestDto claim, Authentication authentication) {
        System.out.println(authentication.getName());
        return ResponseEntity.ok(claimService.createClaim(claim,authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<ClaimResponseDto>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimResponseDto> getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClaimResponseDto>> getClaimsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(claimService.getClaimsByUser(userId));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ClaimResponseDto> updateClaim(@PathVariable Long id, @RequestBody Claim updatedClaim) {
//        return ResponseEntity.ok(claimService.updateClaim(id, updatedClaim));
//    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ClaimResponseDto> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        ClaimResponseDto updated = claimService.updateClaimStatus(id, status);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}
