package com.olvera.digitalbank.repositories;

import com.olvera.digitalbank.entities.TradingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingAccountRepository extends JpaRepository<TradingAccount, Long> {
}
