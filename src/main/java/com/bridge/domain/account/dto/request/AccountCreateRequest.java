package com.bridge.domain.account.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccountCreateRequest {
    @NotNull
    private Long userId;
    @NotBlank // TODO: 계좌 비밀번호
    private String password;
    private String currency;
}
