package com.est.smartrestaurant.domain.dto;

import com.est.smartrestaurant.common.validation.NotSpace;
import com.est.smartrestaurant.domain.entity.Store;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class OrderDTO {
    public record Post(
        @NotBlank
        String name,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String address) {

        public Store toEntity() {
            return Store.builder()
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .build();
        }
    }

    public record Patch(
        @NotSpace
        String name,
        @NotSpace
        String phoneNumber,
        @NotSpace
        String address) {

        public Store toEntity() {
            return Store.builder()
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .build();
        }
    }

    public record Response(
        Long id,
        String name,
        String phoneNumber,
        String address,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
    ) {

        public static Response from(Store store) {
            return new Response(
                store.getId(),
                store.getName(),
                store.getPhoneNumber(),
                store.getAddress(),
                store.getCreatedAt(),
                store.getModifiedAt());
        }
    }
}
