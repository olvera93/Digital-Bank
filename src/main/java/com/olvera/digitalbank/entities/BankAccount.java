package com.olvera.digitalbank.entities;

import com.olvera.digitalbank.enums.AccountStatement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    private String bankAccountId;

    private double balance;

    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private AccountStatement accountStatement;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "bankAccount")
    private List<TradingAccount> tradingAccounts;
}
