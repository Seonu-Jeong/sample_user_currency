package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.ExchangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeInfoDto {
    private User user;

    private Currency currency;

    private BigDecimal amountInKrw;

    private BigDecimal amountAfterExchange;

    private ExchangeStatus status;

}
