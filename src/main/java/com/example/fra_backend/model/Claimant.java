package com.example.fra_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "claimants")
@Data
@NoArgsConstructor          // no-args constructor (required by JPA)
@AllArgsConstructor         // all-args constructor
@Builder                    // enables Claimant.builder() pattern
public class Claimant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claimant_id")
    private Long claimantId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "tribe", length = 100)
    private String tribe;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "block", length = 100)
    private String block;

    @Column(name = "village", length = 100)
    private String village;

    @Column(name = "aadhaar_no", length = 12, unique = true)
    private String aadhaarNo;

    @Column(name = "contact_no", length = 15)
    private String contactNo;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
