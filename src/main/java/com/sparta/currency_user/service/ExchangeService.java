package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeInfoDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.repository.ExchangeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    private final UserService userService;

    private final CurrencyService currencyService;

    public UserCurrency findExchangeById(Long exchangeId) {
        return exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new IllegalArgumentException("환전 내역이 존재하지 않습니다"));
    }

    public ExchangeResponseDto saveExchangeRequest(ExchangeInfoDto exchangeInfoDto) {

        UserCurrency userCurrency = new UserCurrency(
                exchangeInfoDto.getUser(),
                exchangeInfoDto.getCurrency(),
                exchangeInfoDto.getAmountAfterExchange(),
                exchangeInfoDto.getAmountAfterExchange(),
                exchangeInfoDto.getStatus()
        );

        UserCurrency savedUserCurrency = exchangeRepository.save(userCurrency);

        return new ExchangeResponseDto(savedUserCurrency);
    }

    public List<ExchangeResponseDto> getExchangeInfos(Long userId) {
        List<UserCurrency> resultList =  exchangeRepository.findByUserId(userId);

        return resultList.stream()
                .map(ExchangeResponseDto::new)
                .toList();
    }

    @Transactional
    public ExchangeResponseDto cancelExchangeRequest(Long exchangeId) {
        UserCurrency userCurrency = findExchangeById(exchangeId);

        userCurrency.setStatus(ExchangeStatus.CANCELED);

        return new ExchangeResponseDto(userCurrency);
    }
}
