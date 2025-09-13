package com.example.fra_backend.mapper;

import com.example.fra_backend.dto.ClaimResponseDto;
import com.example.fra_backend.model.Claim;

public class ClaimMapper {

    public static ClaimResponseDto toDTO(Claim claim) {
        ClaimResponseDto dto = new ClaimResponseDto();
        dto.setId(claim.getId());
        dto.setClaimantName(claim.getClaimantName());
        dto.setClaimType(claim.getClaimType().name());
        dto.setStatus(claim.getStatus().name());
        dto.setDateFiled(claim.getDateFiled());
        dto.setUsername(claim.getUser().getUsername()); // only username, not whole user
        return dto;
    }
}