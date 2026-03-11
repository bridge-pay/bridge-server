package com.bridge.domain.account.service;

import com.bridge.domain.account.dto.request.AccountCreateRequest;
import com.bridge.domain.account.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountCreateRequest request);
    List<AccountResponse> getAccounts(Long userId);
    AccountResponse getAccount(Long userId, Long accountId);
    void deleteAccount(Long userId, Long accountId);
}
