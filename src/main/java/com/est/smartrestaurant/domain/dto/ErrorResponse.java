package com.est.smartrestaurant.domain.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ErrorResponse {
    private final List<FieldError> fieldErrors;

    private ErrorResponse(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult));
    }

    record FieldError (String field, Object rejectedValue, String reason){
        static List<FieldError> of (BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                bindingResult.getFieldErrors();
            return fieldErrors.stream()
                .map(error -> new FieldError(
                    error.getField(),
                    error.getRejectedValue() == null ?
                        "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()))
                .collect(Collectors.toList());
        }
    }
}
