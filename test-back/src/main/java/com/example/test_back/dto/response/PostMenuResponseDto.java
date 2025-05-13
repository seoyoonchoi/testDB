package com.example.test_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostMenuResponseDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private RestaurantResponseDto restaurant;
}
