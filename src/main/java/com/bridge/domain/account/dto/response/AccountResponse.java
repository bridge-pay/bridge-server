package com.bridge.domain.account.dto.response;

import com.bridge.domain.account.entity.Account;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class AccountResponse {

    private Long accountId;
    private Long userId;
    private String accountNumber;
    private String state;
    private String currency;
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private BigDecimal dailyLimit;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .userId(account.getUser().getUserId())
                .accountNumber(account.getAccountNumber())
                .state(account.getState().name())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .transferLimit(account.getTransferLimit())
                .dailyLimit(account.getDailyLimit())
                .build();
    }
}
