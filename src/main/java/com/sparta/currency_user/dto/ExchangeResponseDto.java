package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.ExchangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeResponseDto {
    private Long id;

    private String currencyName;

    private BigDecimal amountInKrw;

    private BigDecimal amountAfterExchange;

    private ExchangeStatus status;

    public  ExchangeResponseDto(UserCurrency userCurrency){
        this.id = userCurrency.getId();
        this.currencyName = userCurrency.getCurrency().getCurrencyName();
        this.amountInKrw = userCurrency.getAmountInKrw();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.status = userCurrency.getStatus();
    }

}
