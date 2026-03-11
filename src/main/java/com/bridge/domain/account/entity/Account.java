package com.bridge.domain.account.entity;

import com.bridge.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column
    private String currency;

    @Builder
    private Account(User user, String accountNumber, String currency) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.currency = currency != null ? currency : "KRW";
        this.state = AccountState.AVAILABLE;
    }

    public static Account create(User user, String accountNumber,String currency) {
        return Account.builder()
                .user(user)
                .accountNumber(accountNumber)
                .currency(currency)
                .build();
    }

    public void delete() {
        this.state = AccountState.UNAVAILABLE;
    }
}
