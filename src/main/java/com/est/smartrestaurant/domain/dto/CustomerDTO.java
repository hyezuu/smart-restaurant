package com.est.smartrestaurant.domain.dto;

import com.est.smartrestaurant.common.validation.NotSpace;
import com.est.smartrestaurant.domain.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerDTO {

    private final static String DEFAULT_PATTERN_MESSAGE =
        "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다";

    public record Post(
        @NotBlank(message = "이름은 공백일 수 없습니다.")
        String name,
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = DEFAULT_PATTERN_MESSAGE)
        String phoneNumber,
        @NotBlank(message = "주소는 공백일 수 없습니다.")
        String address
    ) {

        public Customer toEntity() {
            return Customer.builder()
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .build();
        }
    }

    public record Patch(
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = DEFAULT_PATTERN_MESSAGE)
        String phoneNumber,
        @NotSpace(message = "주소는 공백일 수 없습니다.")
        String address
    ) {

        public Customer toEntity() {
            return Customer.builder()
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
        LocalDate createdAt,
        LocalDate modifiedAt
    ) {

        public static Response from(Customer customer) {
            return new Response(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getCreatedAt(),
                customer.getModifiedAt()
            );
        }
    }
}
