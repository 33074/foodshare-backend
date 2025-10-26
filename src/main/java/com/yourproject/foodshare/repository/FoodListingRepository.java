package com.yourproject.foodshare.repository;

import com.yourproject.foodshare.entity.FoodListing;
import com.yourproject.foodshare.entity.FoodStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodListingRepository extends JpaRepository<FoodListing, Integer> {

    // Find all available food
    List<FoodListing> findByStatus(FoodStatus status);

    // Find available food by category
    List<FoodListing> findByStatusAndCategory(FoodStatus status, String category);

    // Find available food by title (for search bar)
    List<FoodListing> findByStatusAndTitleContainingIgnoreCase(FoodStatus status, String title);
}
