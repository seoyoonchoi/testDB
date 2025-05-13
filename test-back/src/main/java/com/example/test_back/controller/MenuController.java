package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    //새로운 Menu를 특정 Restaurant에 추가하는 API
    @PostMapping
    public ResponseEntity<PostMenuResponseDto> createMenu(
            @PathVariable Long restaurantId,
            @Valid @RequestBody PostMenuRequestDto dto
            ){
        PostMenuResponseDto responseDto = menuService.createMenu(restaurantId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //특정 Restaurant에 속한 모든 메뉴 조회
    @GetMapping
    public ResponseEntity<List<MenuResponseDto>> getAllMenus(
            @PathVariable Long restaurantId
    ){
        return menuService.getAllMenus(restaurantId);
    }

    //특정 Menu 정보를 ID를 통해 조회
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenusById(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId
    ){
        return menuService.getmenusByid(restaurantId, menuId);
    }

    //Menu정보수정
    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponseDto> updateMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId,
            @Valid @RequestBody PostMenuRequestDto dto
    ){
        MenuResponseDto responseDto = menuService.updateMenu(restaurantId, menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //Menu 개별 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId
    ){
        menuService.deleteMenu(restaurantId, menuId);
        return ResponseEntity.noContent().build();

    }
}





























