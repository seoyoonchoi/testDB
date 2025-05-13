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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        MenuResponseDto responseDto = null;
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_MENU + menuId));

        if(!menu.getRestaurant().getId().equals(restaurantId)){
            throw new IllegalArgumentException("Menu does not belong to the specified Restaurant");
        }

        menu.setName(dto.getName());
        Menu updatedMenu = menuRepository.save(menu);

        responseDto = MenuResponseDto.builder()
                .id(updatedMenu.getId())
                .name(updatedMenu.getName())
                .price(updatedMenu.getPrice())
                .description(updatedMenu.getDescription())
                .build();

        return responseDto;
    }

    @Override
    public void deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_MENU+menuId));

        if(!menu.getRestaurant().getId().equals(restaurantId)){
            throw new IllegalArgumentException("menu does not belont to the restaurant");
        }

        menu.getRestaurant().removeMenu(menu);
        menuRepository.delete(menu);
    }
}
