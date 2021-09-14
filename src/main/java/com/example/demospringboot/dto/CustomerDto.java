package com.example.demospringboot.dto;

import com.example.demospringboot.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;

public class CustomerDto {

    private Long id;

    @Schema(description = "Name of the customer.", example = "Vasil", required = true)
    private String firstName;

    private String lastName;

    private String phone;

    private Boolean isDeleted = Boolean.FALSE;

    private Order order;

}
