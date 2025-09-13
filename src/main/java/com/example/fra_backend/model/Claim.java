package com.example.fra_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimantName;

    @Enumerated(EnumType.STRING)
    private ClaimType claimType;  // INDIVIDUAL, COMMUNITY, HABITAT

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;   // PENDING, APPROVED, REJECTED

    private LocalDate dateFiled;

//    private String supportingDocuments; // could be a file path or link

    // 🔗 Relations (we’ll fill these step by step later)
    // Example: one claim is filed by one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters and setters
}
