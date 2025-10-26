package com.yourproject.foodshare.service;

import com.yourproject.foodshare.dto.ClaimRequest;
import com.yourproject.foodshare.entity.Claim;
import com.yourproject.foodshare.entity.FoodListing;
import com.yourproject.foodshare.entity.FoodStatus;
import com.yourproject.foodshare.entity.User;
import com.yourproject.foodshare.repository.ClaimRepository;
import com.yourproject.foodshare.repository.FoodListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final FoodListingRepository foodListingRepository;

    @Transactional
    // --- THIS IS THE FIX ---
    // We accept the user, which can be null if they are a guest
    public Claim createClaim(ClaimRequest request, User recipient) {
        
        FoodListing foodListing = foodListingRepository.findById(request.getFoodId())
                .orElseThrow(() -> new RuntimeException("Food listing not found"));

        if (foodListing.getStatus() != FoodStatus.AVAILABLE) {
            throw new RuntimeException("This food has already been claimed.");
        }

        foodListing.setStatus(FoodStatus.CLAIMED);
        foodListingRepository.save(foodListing);

        Claim newClaim = Claim.builder()
                .foodListing(foodListing)
                .recipient(recipient) // <-- This will be null for guests, or the user if logged in
                .recipientEmail(request.getEmail())
                .recipientLocation(request.getLocation())
                .recipientPhone(request.getPhone())
                .status(com.yourproject.foodshare.entity.ClaimStatus.PENDING)
                .build();
        
        return claimRepository.save(newClaim);
    }

    // --- NEW METHOD ---
    public List<Claim> getClaimsForUser(User user) {
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return claimRepository.findByRecipientId(user.getId());
    }
}
