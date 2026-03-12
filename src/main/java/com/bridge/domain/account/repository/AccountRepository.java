package com.bridge.domain.account.repository;

import com.bridge.domain.account.entity.Account;
import com.bridge.domain.account.entity.AccountState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a " +
            "JOIN FETCH a.user " +
            "WHERE a.user.userId = :userId " +
            "AND a.state = 'AVAILABLE'")
    List<Account> findAllByUserUserId(Long userId);

    Optional<Account> findByAccountIdAndUserUserIdAndState(Long accountId, Long userId, AccountState state);
}
