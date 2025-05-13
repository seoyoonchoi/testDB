package com.example.test_back.service;

import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuService {
    PostMenuResponseDto createMenu(Long restaurantId, @Valid PostMenuRequestDto dto);
    ResponseEntity<List<MenuResponseDto>> getAllMenus(Long restaurantId);
    ResponseEntity<MenuResponseDto> getmenusByid(Long restaurantId, Long menuId);
    MenuResponseDto updateMenu(Long restaurantId, Long menuId, @Valid PostMenuRequestDto dto);
    void deleteMenu(Long restaurantId, Long menuId);



}
