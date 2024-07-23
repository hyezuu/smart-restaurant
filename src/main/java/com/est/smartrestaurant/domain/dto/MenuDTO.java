package com.est.smartrestaurant.domain.dto;

import com.est.smartrestaurant.common.validation.NotSpace;
import com.est.smartrestaurant.domain.entity.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public class MenuDTO {

    public record Post(
        @NotBlank
        String name,
        @NotBlank
        String category,
        @NotNull
        Double price,
        @NotBlank
        String description) {

        public Menu toEntity() {
            return Menu.builder()
                .name(this.name)
                .category(this.category)
                .price(this.price)
                .description(this.description).build();
        }
    }

    public record Patch(
        @NotSpace
        String name,
        @NotSpace
        String category,
        @Positive
        Double price,
        @NotSpace
        String description) {

        public Menu toEntity() {
            return Menu.builder()
                .name(this.name)
                .category(this.category)
                .price(this.price)
                .description(this.description).build();
        }
    }

    public record Response(
        Long id,
        String name,
        String category,
        Double price,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
    ) {

        public static Response from(Menu menu) {
            return new Response(
                menu.getId(),
                menu.getName(),
                menu.getCategory(),
                menu.getPrice(),
                menu.getDescription(),
                menu.getCreatedAt(),
                menu.getModifiedAt());
        }
    }
}
