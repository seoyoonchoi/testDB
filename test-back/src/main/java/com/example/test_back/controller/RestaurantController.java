package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    //새로운 Restaurant을 추가하는 API
    @PostMapping
    public ResponseEntity<PostRestaurantResponseDto> createRestaurant(
            @Valid @RequestBody PostRestaurantRequestDto dto
            ){
        PostRestaurantResponseDto responseDto = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //Restaurant ID를 통해 단일 레스토랑 정보 조회
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(
            @PathVariable Long restaurantId
    ){
        RestaurantResponseDto responseDto = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //Restaurant 목록 전체 조회 (페이징 기능 추가)
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        List<RestaurantResponseDto> responseDtos = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    //Restaurant 정보 수정
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(
            @PathVariable Long restaurantId,
            @Valid @RequestBody PostRestaurantRequestDto dto
    ){
        RestaurantResponseDto responseDto = restaurantService.updateRestaurant(restaurantId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //Restaurant 삭제 (하위 메뉴들도 함께 삭제 처리)
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable Long restaurantId
    ){
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();

    }

}
