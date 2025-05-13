package com.example.test_back.service.impl;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.entity.Menu;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.MenuRepository;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    //메뉴 생성
    @Override
    public PostMenuResponseDto createMenu(Long restaurantId, PostMenuRequestDto dto) {
        MenuResponseDto responseDto = null;
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_RESTAURANT+restaurantId));

        Menu newMenu = Menu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();

        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);
        responseDto = PostMenuResponseDto.builder()
                .id(savedMenu.getId())
                .name(savedMenu.getName())
                .description(savedMenu.getDescription())
                .restaurant(savedMenu.getRestaurant(
                        restaurant.getId()


                ))
                .build();


        return null;
    }

    @Override
    public ResponseEntity<List<MenuResponseDto>> getAllMenus(Long restaurantId) {
        List<MenuResponseDto> responseDtos = null;
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_RESTAURANT+restaurantId));

        return null;
    }

    @Override
    public ResponseEntity<MenuResponseDto> getmenusByid(Long restaurantId, Long menuId) {
        return null;
    }

    @Override
    public MenuResponseDto updateMenu(Long restaurantId, Long menuId, PostMenuRequestDto dto) {
        return null;
    }

    @Override
    public void deleteMenu(Long restaurantId, Long menuId) {

    }
}
