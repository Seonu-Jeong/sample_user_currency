package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.*;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.service.CurrencyService;
import com.sparta.currency_user.service.ExchangeService;
import com.sparta.currency_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExchangeController {

    private final CurrencyService currencyService;

    private final UserService userService;

    private final ExchangeService exchangeService;

    @PostMapping("/users/{userId}/exchanges")
    public ResponseEntity<ExchangeResponseDto> requestExchange(
            @PathVariable Long userId,
            @Valid @RequestBody ExchangeRequestDto exchangeRequestDto
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
                .body(exchangeService.saveExchangeRequest(exchangeInfoDto));
    }


    @GetMapping("/users/{userId}/exchanges")
    public ResponseEntity<List<ExchangeResponseDto>> readExchangeList(
            @PathVariable Long userId
    ){

        /* 유저 가져오기 */
        User user = userService.findUserById(userId);

        /* 유저의 id로 유저의 환전 요청 내역 조회 */
        List<ExchangeResponseDto> resultList = exchangeService.getExchangeInfos(user.getId());

        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @PatchMapping("/users/{userId}/exchanges/{exchangeId}")
    public ResponseEntity<ExchangeResponseDto> cancleExchange(
            @PathVariable Long userId,
            @PathVariable Long exchangeId
    ) {
        /* 유저 검증 */
        User user = userService.findUserById(userId);

        /* 환전 내역 취소 */
        ExchangeResponseDto exchangeResponseDto = exchangeService.cancelExchangeRequest(exchangeId);

        return ResponseEntity.status(HttpStatus.OK).body(exchangeResponseDto);
    }


}
