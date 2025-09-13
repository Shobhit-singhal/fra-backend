package com.example.fra_backend.dto;

import com.example.fra_backend.model.ClaimType;
import lombok.Data;

@Data
public class ClaimRequestDto {
    private String claimantName;
    private ClaimType claimType;
    // no dateFiled → will be set automatically
}
