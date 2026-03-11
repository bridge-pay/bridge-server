package com.bridge.domain.account.service;

import com.bridge.domain.account.dto.request.AccountCreateRequest;
import com.bridge.domain.account.dto.response.AccountResponse;
import com.bridge.domain.account.entity.Account;
import com.bridge.domain.account.entity.AccountState;
import com.bridge.domain.account.repository.AccountRepository;
import com.bridge.domain.user.entity.User;
import com.bridge.domain.user.repository.UserRepository;
import com.bridge.global.exception.BusinessException;
import com.bridge.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public AccountResponse createAccount(AccountCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        String accountNumber = generateAccountNumber();
        Account account = Account.create(user, accountNumber, request.getCurrency());
        accountRepository.save(account);
        return AccountResponse.from(account);
    }

    @Override
    public List<AccountResponse> getAccounts(Long userId) {
        return accountRepository.findAllByUserUserId(userId)
                .stream()
                .map(AccountResponse::from)
                .toList();
    }

    @Override
    public AccountResponse getAccount(Long userId, Long accountId) {
        Account account = getAccountById(userId, accountId);
        return AccountResponse.from(account);
    }

    @Transactional
    @Override
    public void deleteAccount(Long userId, Long accountId) {
        Account account = getAccountById(userId, accountId);
        account.delete();
    }

    private String generateAccountNumber() {
        // TODO: 계좌번호 생성 로직 수정
        return "B" + System.currentTimeMillis();
    }

    private Account getAccountById(Long userId, Long accountId) {
        return accountRepository.findByAccountIdAndUserUserIdAndState(accountId, userId, AccountState.AVAILABLE)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND));
    }
}
