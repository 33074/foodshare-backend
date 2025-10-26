package com.yourproject.foodshare.controller;

import com.yourproject.foodshare.dto.ClaimRequest;
import com.yourproject.foodshare.entity.Claim;
import com.yourproject.foodshare.entity.User;
import com.yourproject.foodshare.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<?> createClaim(
            @Valid @RequestBody ClaimRequest request,
            @AuthenticationPrincipal User user // <-- User is optional (can be null if not logged in)
    ) {
        try {
            // Pass both the request and the (potentially null) user
            Claim newClaim = claimService.createClaim(request, user);
            return ResponseEntity.ok(newClaim);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- NEW ENDPOINT ---
    @GetMapping("/my-claims")
    public ResponseEntity<List<Claim>> getMyClaims(
            @AuthenticationPrincipal User user // Here, user is required
    ) {
        if (user == null) {
             // This will be caught by SecurityConfig, but good to have
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(claimService.getClaimsForUser(user));
    }
}
