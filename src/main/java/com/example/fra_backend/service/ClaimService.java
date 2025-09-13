package com.example.fra_backend.service;

import com.example.fra_backend.dto.ClaimRequestDto;
import com.example.fra_backend.dto.ClaimResponseDto;
import com.example.fra_backend.mapper.ClaimMapper;
import com.example.fra_backend.model.Claim;
import com.example.fra_backend.model.ClaimStatus;
import com.example.fra_backend.model.User;
import com.example.fra_backend.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    @Autowired
    UserService userService;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public ClaimResponseDto createClaim(ClaimRequestDto dto,String name) {
        User user=userService.getUserByUsername(name);
        Claim claim = new Claim();
        claim.setClaimantName(dto.getClaimantName());
        claim.setClaimType(dto.getClaimType());
        claim.setStatus(ClaimStatus.PENDING);
        claim.setDateFiled(LocalDate.now());
        claim.setUser(user);


        claimRepository.save(claim);

        ClaimResponseDto res = new ClaimResponseDto();
        res.setId(claim.getId());
        res.setClaimantName(claim.getClaimantName());
        res.setClaimType(String.valueOf(claim.getClaimType()));
        res.setStatus(String.valueOf(claim.getStatus()));
        res.setDateFiled(claim.getDateFiled());
        res.setUsername(claim.getUser().getUsername());
        return res;
    }

    public List<ClaimResponseDto> getAllClaims() {
        return claimRepository.findAll()
                .stream()
                .map(ClaimMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClaimResponseDto> getClaimById(Long id) {
        return claimRepository.findById(id)
                .map(ClaimMapper::toDTO);
    }

    public List<ClaimResponseDto> getClaimsByUser(Long userId) {
        return claimRepository.findByUserId(userId)
                .stream()
                .map(ClaimMapper::toDTO)
                .collect(Collectors.toList());
    }

//    public ClaimResponseDto updateClaim(Long id, Claim updatedClaim) {
//        return claimRepository.findById(id)
//                .map(claim -> {
//                    claim.setClaimantName(updatedClaim.getClaimantName());
//                    claim.setClaimType(updatedClaim.getClaimType());
//                    claim.setStatus(updatedClaim.getStatus());
//                    Claim saved = claimRepository.save(claim);
//                    return ClaimMapper.toDTO(saved);
//                })
//                .orElseThrow(() -> new RuntimeException("Claim not found"));
//    }

    public ClaimResponseDto updateClaimStatus(Long id, String newStatus) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(Enum.valueOf(ClaimStatus.class, newStatus.toUpperCase()));
        Claim saved = claimRepository.save(claim);

        return ClaimMapper.toDTO(saved);
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}
