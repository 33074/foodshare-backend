package com.yourproject.foodshare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClaimRequest {

    @NotNull
    private Integer foodId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String location;

    @NotBlank
    private String phone;
}
