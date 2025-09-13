package com.example.fra_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.locationtech.jts.geom.Polygon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "land_parcels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandParcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_id")
    private Long parcelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private FraClaim fraClaim;   // Link to FRA Claim

    @Column(name = "geom", columnDefinition = "Geometry(Polygon,4326)")
    private Polygon geom;        // PostGIS Polygon

    @Column(name = "area_ha", precision = 10, scale = 2)
    private BigDecimal areaHa;   // area in hectares

    @Column(name = "land_use", length = 100)
    private String landUse;      // e.g. agriculture, forest

    @Column(name = "verified")
    private Boolean verified = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

