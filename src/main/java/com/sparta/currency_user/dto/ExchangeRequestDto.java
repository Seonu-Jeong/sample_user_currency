package com.sparta.currency_user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotBlank
    private String currencyName;

    @NotNull
    private BigDecimal amountInKrw;

}
