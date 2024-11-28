package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.*;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.service.CurrencyService;
import com.sparta.currency_user.service.ExchangeService;
import com.sparta.currency_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@RestController
public class ExchangeController {

    private final CurrencyService currencyService;

    private final UserService userService;

    private final ExchangeService exchangeService;

    @PostMapping("/users/{userId}/exchanges")
    public ResponseEntity<ExchangeResponseDto> requestExchange(
            @PathVariable Long userId,
            @RequestBody ExchangeRequestDto exchangeRequestDto
    ){

        /* 유저 가져오기 */
        User user = userService.findUserById(userId);

        /* 통화이름으로 통화 정보 가져오기 */
        Currency currency = currencyService.findCurrencyByName(exchangeRequestDto.getCurrencyName());

        /* 환전 후 금액 계산 */
        BigDecimal exchangeRate = currency.getExchangeRate();
        BigDecimal amountInKrw = exchangeRequestDto.getAmountInKrw();

        BigDecimal amountAfterExchange = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);

        /* 환전 정보 저장 */
        ExchangeInfoDto exchangeInfoDto = new ExchangeInfoDto(
                user,
                currency,
                amountInKrw,
                amountAfterExchange,
                ExchangeStatus.NORMAL
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(exchangeService.save(exchangeInfoDto));
    }
}
