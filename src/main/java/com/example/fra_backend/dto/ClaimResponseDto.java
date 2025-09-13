package com.example.fra_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClaimResponseDto {
    private Long id;
    private String claimantName;
    private String claimType;
    private String status;
    private LocalDate dateFiled;
    private String username; // instead of embedding full User
}