package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    private String currencyName;

    private BigDecimal amountInKrw;

}
