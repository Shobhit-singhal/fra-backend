package com.example.fra_backend.service;

import com.example.fra_backend.model.Claim;
import com.example.fra_backend.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByUserId(userId);
    }

    public Claim updateClaim(Long id, Claim updatedClaim) {
        return claimRepository.findById(id).map(claim -> {
            claim.setClaimantName(updatedClaim.getClaimantName());
            claim.setClaimType(updatedClaim.getClaimType());
            claim.setStatus(updatedClaim.getStatus());
//            claim.setSupportingDocuments(updatedClaim.getSupportingDocuments());
            return claimRepository.save(claim);
        }).orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}
