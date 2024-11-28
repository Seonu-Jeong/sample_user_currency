package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.ExchangeTotalInfoDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<UserCurrency,Long> {

    List<UserCurrency> findByUserId(Long userId);


    @Query("select new com.sparta.currency_user.dto.ExchangeTotalInfoDto(count(uc), sum(uc.amountInKrw)) " +
            "from UserCurrency uc where uc.user = :user and uc.status = 'NORMAL' group by uc.user")
    Optional<ExchangeTotalInfoDto> findUsersTotalExchangeInfo(
            @Param("user") User user
    );


}
