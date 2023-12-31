package com.olvera.digitalbank.repositories;

import com.olvera.digitalbank.entities.TradingAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingAccountRepository extends JpaRepository<TradingAccount, String> {

    List<TradingAccount> findByBankAccount_BankAccountId(String bankAccountId);

    Page<TradingAccount> findByBankAccount_BankAccountId(String bankAccountId, Pageable pageable);

    Page<TradingAccount> findByBankAccount_BankAccountIdOrderByTradingDateDesc(String bankAccountId, Pageable pageable);

}
