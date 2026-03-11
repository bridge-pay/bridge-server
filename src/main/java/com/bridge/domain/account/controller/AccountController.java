package com.bridge.domain.account.controller;

import com.bridge.domain.account.dto.request.AccountCreateRequest;
import com.bridge.domain.account.dto.response.AccountResponse;
import com.bridge.domain.account.service.AccountService;
import com.bridge.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Account API",
        description = "계좌 생성, 조회, 삭제를 담당하는 API"
)

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "계좌 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> createAccount(
            @Valid @RequestBody AccountCreateRequest request
            ) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "계좌 목록 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAccounts(
            @PathVariable Long userId
    ) {
        // TODO: Spring Security 적용
        List<AccountResponse> response = accountService.getAccounts(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "계좌 상세 조회")
    @GetMapping("/{userId}/{accountId}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccount(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "accountId") Long accountId
    ) {
        // TODO
        AccountResponse response = accountService.getAccount(userId, accountId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "계좌 삭제")
    @DeleteMapping("/{userId}/{accountId}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "accountId") Long accountId
    ) {
        // TODO
        accountService.deleteAccount(userId, accountId);
        return ResponseEntity.noContent().build();
    }
}
