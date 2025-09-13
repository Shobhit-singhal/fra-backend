package com.example.fra_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name = "claimant_schemes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimantScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claimant_id", nullable = false)
    private Claimant claimant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheme_id", nullable = false)
    private GovtScheme govtScheme;

    @Column(name = "eligible")
    private Boolean eligible;

    @Column(name = "enrolled")
    private Boolean enrolled = false;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
}

