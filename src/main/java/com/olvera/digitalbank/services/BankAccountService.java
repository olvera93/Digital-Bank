package com.olvera.digitalbank.services;

import com.olvera.digitalbank.entities.BankAccount;
import com.olvera.digitalbank.entities.Client;
import com.olvera.digitalbank.entities.CurrentAccount;
import com.olvera.digitalbank.entities.SavingAccount;
import com.olvera.digitalbank.exeptions.BankAccountNotFoundException;
import com.olvera.digitalbank.exeptions.ClientNotFoundException;
import com.olvera.digitalbank.exeptions.InsufficientBalanceException;

import java.util.List;

public interface BankAccountService {

    Client saveClient(Client client);

    CurrentAccount saveBankCurrentAccount(double balanceInitial, double overdraft, Long clientId) throws ClientNotFoundException;

    SavingAccount saveBankSavingAccount(double balanceInitial, double interestRate, Long clientId) throws ClientNotFoundException;

    List<Client> getClients();

    BankAccount getBankAccount(String bankAccountId) throws BankAccountNotFoundException;

    void debit(String bankAccountId, double amount, String description) throws BankAccountNotFoundException, InsufficientBalanceException;

    void credit(String bankAccountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountOwnerId, String accountDestinationId, double amount) throws BankAccountNotFoundException, InsufficientBalanceException;

    List<BankAccount> getBankAccounts();

}
