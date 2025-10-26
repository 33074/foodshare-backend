package com.yourproject.foodshare.repository;

import com.yourproject.foodshare.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // <-- Import List

public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    // --- NEW METHOD ---
    // Finds all claims associated with a specific user ID
    List<Claim> findByRecipientId(Integer recipientId);
}
