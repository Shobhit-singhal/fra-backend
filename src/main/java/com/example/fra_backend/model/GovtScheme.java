package com.example.fra_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.Type;
import com.vladmihalcea.hibernate.type.json.JsonType;


import java.util.Map;

@Entity
@Table(name = "govt_schemes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GovtScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheme_id")
    private Long schemeId;

    @Column(name = "scheme_name", length = 255, nullable = false)
    private String schemeName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Type(JsonType.class)
    @Column(name = "eligibility_criteria", columnDefinition = "jsonb")
    private Map<String, Object> eligibilityCriteria;
}

