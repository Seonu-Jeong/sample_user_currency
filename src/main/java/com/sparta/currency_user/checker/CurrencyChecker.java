package com.sparta.currency_user.checker;

import com.sparta.currency_user.dto.CurrencyResponseDto;
import com.sparta.currency_user.service.CurrencyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CurrencyChecker {

    private final CurrencyService currencyService;

    @PostConstruct
    public void currencyTableCheck() {
        List<CurrencyResponseDto> currencyResponseDtos =  currencyService.findAll();

        long result = currencyResponseDtos.stream()
                .filter(dto ->
                        dto.getExchangeRate().compareTo(BigDecimal.ZERO)<=0)
                .count();

        if(result > 0)
            log.info("환율 테이블에 유효하지 않은 값 존재");

    }

}
