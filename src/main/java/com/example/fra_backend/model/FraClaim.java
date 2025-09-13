package com.example.fra_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fra_claims")
@Data
public class FraClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long claimId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claimant_id", nullable = false)
    private Claimant claimant;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_type", length = 50, nullable = false)
    private ClaimType claimType; // individual, community

    @Column(name = "claim_date")
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private ClaimStatus status; // pending, verified, approved, rejected

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verified_by")
    private User verifiedBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
