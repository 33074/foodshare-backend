package com.yourproject.foodshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private FoodListing foodListing;

    // --- THIS IS THE FIX ---
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = true) // <-- This must be true
    @JsonIgnore // Prevent loops
    private User recipient;

    @Builder.Default
    private LocalDateTime claimDate = LocalDateTime.now();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ClaimStatus status = ClaimStatus.PENDING;

    // Details from the modal
    @Column(nullable = false)
    private String recipientEmail;

    @Column(nullable = false)
    private String recipientLocation;

    @Column(nullable = false)
    private String recipientPhone;
}
