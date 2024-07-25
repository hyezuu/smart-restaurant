package com.est.smartrestaurant.controller;

import com.est.smartrestaurant.domain.dto.StoreDTO;
import com.est.smartrestaurant.domain.dto.StoreDTO.Response;
import com.est.smartrestaurant.service.StoreService;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RequestMapping("/stores")
@RestController
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<URI> createStore(@RequestBody StoreDTO.Post postDto) {
        Long id = storeService.save(postDto.toEntity()).getId();
        URI uri = UriComponentsBuilder
            .newInstance().path("/stores/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO.Response> getStore(@PathVariable("id") @Positive Long id) {
        return ResponseEntity.ok(Response.from(storeService.findById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StoreDTO.Response> updateStore(
        @PathVariable("id") @Positive Long id,
        @RequestBody StoreDTO.Patch patch) {
        return ResponseEntity.ok(Response.from(storeService.update(id, patch.toEntity())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") @Positive Long id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
