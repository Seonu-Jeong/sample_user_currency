package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_currency")
public class UserCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    private Currency currency;

    private Long amount_id_krw;

    private BigDecimal amount_after_exchange;

    private String status;

    public UserCurrency(User user, Currency currency, Long amount_id_krw, BigDecimal amount_after_exchange, String status) {
        this.user = user;
        this.currency = currency;
        this.amount_id_krw = amount_id_krw;
        this.amount_after_exchange = amount_after_exchange;
        this.status = status;
    }

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
