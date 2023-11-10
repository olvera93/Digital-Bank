package com.olvera.digitalbank.entities;

import com.olvera.digitalbank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradingAccountId;

    private Date tradingDate;

    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    private BankAccount bankAccount;


}
