package com.olvera.digitalbank.services;

import com.olvera.digitalbank.entities.BankAccount;
import com.olvera.digitalbank.entities.CurrentAccount;
import com.olvera.digitalbank.entities.SavingAccount;
import com.olvera.digitalbank.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consult() {
        BankAccount bankAccount = bankAccountRepository.findById("487c5e92-12e0-42a6-a185-4121eb3ad364").orElse(null);

        if (bankAccount != null) {
            System.out.println("*******************");
            System.out.println(bankAccount.getBankAccountId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getAccountStatement());
            System.out.println(bankAccount.getCreationDate());
            System.out.println(bankAccount.getClient().getName());
            System.out.println(bankAccount.getClass().getSimpleName());

            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Overdraft : " + ((CurrentAccount) bankAccount).getOverdraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Interest Rate : " + ((SavingAccount) bankAccount).getInterestRate());

            }

            bankAccount.getTradingAccounts().forEach(tradingAccount -> {
                System.out.println("------------------");
                System.out.println(tradingAccount.getOperationType());
                System.out.println(tradingAccount.getTradingDate());
                System.out.println(tradingAccount.getAmount());
            });
        }
    }
}
