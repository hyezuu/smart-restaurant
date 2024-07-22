package com.est.smartrestaurant.controller;

import com.est.smartrestaurant.domain.dto.CustomerDTO;
import com.est.smartrestaurant.domain.dto.CustomerDTO.Response;
import com.est.smartrestaurant.service.CustomerService;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Validated
@RequestMapping
@RestController("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<URI> createCustomer(@RequestBody CustomerDTO.Post postDto) {
        Long Id = customerService.save(postDto.toEntity()).getId();
        URI uri = UriComponentsBuilder
            .newInstance().path("/customer/{id}").buildAndExpand(Id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO.Response> getCustomer(@PathVariable("id") @Positive Long id) {
        return ResponseEntity.ok(Response.from(customerService.findById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO.Response> updateCustomer(
        @PathVariable("id") @Positive Long id,
        @RequestBody CustomerDTO.Patch patchDto) {
        return ResponseEntity.ok(Response.from(customerService.update(id, patchDto.toEntity())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") @Positive Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
