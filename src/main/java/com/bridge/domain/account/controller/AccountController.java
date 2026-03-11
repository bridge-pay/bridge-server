package com.bridge.domain.account.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Account API",
        description = "계좌 생성, 조회, 삭제를 담당하는 API"
)

@RestController("/accounts")
@RequiredArgsConstructor
public class AccountController {

}
