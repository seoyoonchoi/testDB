package com.example.test_back.service;

import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface RestaurantService {
    PostRestaurantResponseDto createRestaurant(@Valid PostRestaurantRequestDto dto);

    RestaurantResponseDto getRestaurantById(Long restaurantId);

    List<RestaurantResponseDto> getAllRestaurants();

    RestaurantResponseDto updateRestaurant(Long restaurantId, @Valid PostRestaurantRequestDto dto);

    RestaurantResponseDto<Void> deleteRestaurant(Long restaurantId);
}
