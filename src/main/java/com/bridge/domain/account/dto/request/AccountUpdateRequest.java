package com.bridge.domain.account.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountUpdateRequest {
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private BigDecimal dailyLimit;
}
