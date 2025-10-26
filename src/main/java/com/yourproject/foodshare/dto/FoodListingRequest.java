package com.yourproject.foodshare.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FoodListingRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull
    @Min(value = 1, message = "Portions must be at least 1")
    private Integer portions;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDateTime expiresAt;

    private String category;
    
    private String imageUrl;
    
    @NotBlank(message = "Pickup address is required")
    private String pickupAddress;
}
