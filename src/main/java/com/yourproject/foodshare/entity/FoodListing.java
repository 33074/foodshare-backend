package com.yourproject.foodshare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_listings")
public class FoodListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Integer portions;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private String category; // "Fresh", "Baked", "Desi Indian", "Prepared"
    private String imageUrl;
    private String pickupAddress;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private FoodStatus status = FoodStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;
}
