package com.est.smartrestaurant.controller;

import com.est.smartrestaurant.domain.dto.MenuDTO;
import com.est.smartrestaurant.service.MenuService;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RequestMapping("/menus")
@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<URI> createMenu(@RequestBody MenuDTO.Post postDto) {
        Long id = menuService.save(postDto.toEntity()).getId();

        URI uri = UriComponentsBuilder
            .newInstance().path("/menus/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public MenuDTO.Response getMenuById(@PathVariable("id") @Positive Long id) {
        return MenuDTO.Response.from(menuService.findById(id));
    }

    @GetMapping
    public Page<MenuDTO.Response> getMenus
        (@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return menuService.findAll(page, size).map(MenuDTO.Response::from);
    }

    @PatchMapping("/{id}")
    public MenuDTO.Response updateMenu(
        @PathVariable("id") @Positive Long id,
        @RequestBody MenuDTO.Patch patchDto) {
        return MenuDTO.Response.from(menuService.update(id, patchDto.toEntity()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") @Positive Long id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
