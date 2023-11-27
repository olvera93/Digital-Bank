package com.olvera.digitalbank.repositories;

import com.olvera.digitalbank.entities.BankAccount;
import com.olvera.digitalbank.entities.TradingAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
