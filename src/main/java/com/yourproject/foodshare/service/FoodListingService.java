package com.yourproject.foodshare.service;

import com.yourproject.foodshare.dto.FoodListingRequest;
import com.yourproject.foodshare.entity.FoodListing;
import com.yourproject.foodshare.entity.FoodStatus;
import com.yourproject.foodshare.entity.User;
import com.yourproject.foodshare.repository.FoodListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodListingService {

    private final FoodListingRepository foodListingRepository;

    /**
     * Gets all available food, optionally filtered by a search query.
     * This implements your search bar logic.
     */
    public List<FoodListing> getAvailableFood(String searchQuery) {
        if (searchQuery == null || searchQuery.isBlank()) {
            // No search, return all available
            return foodListingRepository.findByStatus(FoodStatus.AVAILABLE);
        } else {
            // Search by title
            return foodListingRepository.findByStatusAndTitleContainingIgnoreCase(
                FoodStatus.AVAILABLE, 
                searchQuery
            );
        }
    }

    /**
     * Gets all available food by a specific category.
     * This implements your category filter logic.
     */
    public List<FoodListing> getAvailableFoodByCategory(String category) {
        return foodListingRepository.findByStatusAndCategory(
            FoodStatus.AVAILABLE, 
            category
        );
    }
    
    /**
     * NEW METHOD
     * Creates and saves a new food listing, associating it with the logged-in donor.
     */
    public FoodListing createFoodListing(FoodListingRequest request, User donor) {
        FoodListing newListing = FoodListing.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .portions(request.getPortions())
                .expiresAt(request.getExpiresAt())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .pickupAddress(request.getPickupAddress())
                .status(FoodStatus.AVAILABLE)
                .donor(donor) // Link the listing to the logged-in user
                .build();
        
        return foodListingRepository.save(newListing);
    }
}
