package com.example.fra_backend.repository;

import com.example.fra_backend.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
    List<Claim> findByUserId(Long userId);
    List<Claim> findByStatus(String status);
}
