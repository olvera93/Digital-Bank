package com.olvera.digitalbank.services;

import com.olvera.digitalbank.dtos.*;
import com.olvera.digitalbank.entities.BankAccount;
import com.olvera.digitalbank.entities.Client;
import com.olvera.digitalbank.entities.CurrentAccount;
import com.olvera.digitalbank.entities.SavingAccount;
import com.olvera.digitalbank.exeptions.BankAccountNotFoundException;
import com.olvera.digitalbank.exeptions.ClientNotFoundException;
import com.olvera.digitalbank.exeptions.InsufficientBalanceException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankAccountService {

    ClientDto saveClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto);

    void deleteClient(Long clientId);

    ClientDto getClient(Long clientId) throws ClientNotFoundException;

    List<ClientDto> searchClients(String keyword);

    CurrentAccountDto saveBankCurrentAccount(double balanceInitial, double overdraft, Long clientId) throws ClientNotFoundException;

    SavingAccountDto saveBankSavingAccount(double balanceInitial, double interestRate, Long clientId) throws ClientNotFoundException;

    List<ClientDto> getClients();

    BankAccountDto getBankAccount(String bankAccountId) throws BankAccountNotFoundException;

    void debit(String bankAccountId, double amount, String description) throws BankAccountNotFoundException, InsufficientBalanceException;

    void credit(String bankAccountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountOwnerId, String accountDestinationId, double amount) throws BankAccountNotFoundException, InsufficientBalanceException;

    List<BankAccountDto> getBankAccounts();

    List<TradingAccountDto> historyAccounts(String bankAccountId);

    HistoryAccountDto getHistoryAccount(String bankAccountId, int page, int size) throws BankAccountNotFoundException;


}
