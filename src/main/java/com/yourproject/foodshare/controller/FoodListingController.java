package com.yourproject.foodshare.controller;

import com.yourproject.foodshare.dto.FoodListingRequest; // We will create this DTO
import com.yourproject.foodshare.entity.FoodListing;
import com.yourproject.foodshare.entity.User;
import com.yourproject.foodshare.service.FoodListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodListingController {

    private final FoodListingService foodListingService;

    /**
     * This endpoint handles both browsing all food AND searching.
     * /api/food -> gets all
     * /api/food?search=salad -> gets food with "salad" in the title
     */
    @GetMapping
    public ResponseEntity<List<FoodListing>> getAvailableFood(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(foodListingService.getAvailableFood(search));
    }

    /**
     * This endpoint handles category filtering
     * /api/food/category?name=Desi Indian
     */
    @GetMapping("/category")
    public ResponseEntity<List<FoodListing>> getFoodByCategory(
            @RequestParam String name
    ) {
        return ResponseEntity.ok(foodListingService.getAvailableFoodByCategory(name));
    }
    
    /**
     * NEW ENDPOINT
     * Creates a new food donation listing.
     * @AuthenticationPrincipal User user: This gets the logged-in user automatically
     */
    @PostMapping("/donate")
    public ResponseEntity<FoodListing> createFoodListing(
            @Valid @RequestBody FoodListingRequest request,
            @AuthenticationPrincipal User user 
    ) {
        // We pass the request DTO and the logged-in user to the service
        FoodListing newListing = foodListingService.createFoodListing(request, user);
        return ResponseEntity.ok(newListing);
    }
}
