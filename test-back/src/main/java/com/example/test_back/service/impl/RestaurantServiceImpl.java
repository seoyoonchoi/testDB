package com.example.test_back.service.impl;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    //레스토랑 생성
    @Override
    @Transactional
    public PostRestaurantResponseDto createRestaurant(PostRestaurantRequestDto dto) {
        PostRestaurantResponseDto responseDto = null;
        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        Restaurant saved = restaurantRepository.save(newRestaurant);

        responseDto = PostRestaurantResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();

        return responseDto;
    }

    //단건조회
    @Override
    @Transactional(readOnly = true)
    public RestaurantResponseDto getRestaurantById(Long restaurantId) {
        RestaurantResponseDto responseDto = null;
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_RESTAURANT+restaurantId));

        responseDto = new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhoneNumber()
        );

        return responseDto;
    }

    //전체조회
    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponseDto> getAllRestaurants() {
        List<RestaurantResponseDto> responseDtos = null;
        List<Restaurant> restaurants = restaurantRepository.findAll();
        responseDtos = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build()
                ).collect(Collectors.toList());
        return responseDtos;
    }

    //레스토랑 업데이트
    @Override
    @Transactional
    public RestaurantResponseDto updateRestaurant(Long restaurantId, PostRestaurantRequestDto dto) {
        RestaurantResponseDto responseDto = null;
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_RESTAURANT+restaurantId));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());

        Restaurant updateRestaurant = restaurantRepository.save(restaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(updateRestaurant.getId())
                .name(updateRestaurant.getName())
                .address(updateRestaurant.getAddress())
                .phoneNumber(updateRestaurant.getPhoneNumber())
                .build();

        return responseDto;
    }

    //레스토랑 삭제
    @Override
    @Transactional
    public RestaurantResponseDto deleteRestaurant(Long restaurantId) {
        if(!restaurantRepository.existsById(restaurantId)){
            throw new EntityNotFoundException(ResponseMessage.NOT_EXIST_RESTAURANT+restaurantId);
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_MENU+restaurantId));

        restaurant.getMenus().forEach(restaurant::removeMenu);
        restaurantRepository.deleteById(restaurantId);
        return null;
    }
}
