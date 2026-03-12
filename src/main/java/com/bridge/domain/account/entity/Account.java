package com.bridge.domain.account.entity;

import com.bridge.domain.account.dto.request.AccountUpdateRequest;
import com.bridge.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountState state;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column
    private String currency;

    @Column
    private LocalDateTime startDate;

    @Column
    private BigDecimal transferLimit;

    @Column
    private BigDecimal dailyLimit;

    @Builder
    private Account(User user, String accountNumber, String currency,
                    BigDecimal transferLimit, BigDecimal dailyLimit) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.state = AccountState.AVAILABLE;
        this.balance = BigDecimal.valueOf(0);
        this.currency = currency != null ? currency : "KRW";
        this.startDate = LocalDateTime.now();
        this.transferLimit = transferLimit;
        this.dailyLimit = dailyLimit;
    }

    public static Account create(User user, String accountNumber,String currency,
                                 BigDecimal transferLimit, BigDecimal dailyLimit) {
        return Account.builder()
                .user(user)
                .accountNumber(accountNumber)
                .currency(currency)
                .transferLimit(transferLimit)
                .dailyLimit(dailyLimit)
                .build();
    }

    public void update(BigDecimal balance, BigDecimal transferLimit, BigDecimal dailyLimit) {
        if (balance != null) this.balance = balance;
        if (transferLimit != null) this.transferLimit = transferLimit;
        if (dailyLimit != null) this.dailyLimit = dailyLimit;
    }

    public void delete() {
        this.state = AccountState.UNAVAILABLE;
    }


}
