package com.olvera.digitalbank.services.impl;

import com.olvera.digitalbank.dtos.*;
import com.olvera.digitalbank.entities.*;
import com.olvera.digitalbank.enums.OperationType;
import com.olvera.digitalbank.exeptions.BankAccountNotFoundException;
import com.olvera.digitalbank.exeptions.ClientNotFoundException;
import com.olvera.digitalbank.exeptions.InsufficientBalanceException;
import com.olvera.digitalbank.mappers.BankAccountMapperImpl;
import com.olvera.digitalbank.repositories.BankAccountRepository;
import com.olvera.digitalbank.repositories.ClientRepository;
import com.olvera.digitalbank.repositories.TradingAccountRepository;
import com.olvera.digitalbank.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private BankAccountMapperImpl bankAccountMapper;

    @Override
    public ClientDto saveClient(ClientDto clientDto) {

        log.info("Save a new client");
        Client client = bankAccountMapper.dtoToClient(clientDto);
        client = clientRepository.save(client);
        return bankAccountMapper.clientToDto(client);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        log.info("Updating client");
        Client client = bankAccountMapper.dtoToClient(clientDto);
        client = clientRepository.save(client);
        return bankAccountMapper.clientToDto(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public ClientDto getClient(Long clientId) throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));
        return bankAccountMapper.clientToDto(client);
    }

    @Override
    public CurrentAccountDto saveBankCurrentAccount(double balanceInitial, double overdraft, Long clientId) throws ClientNotFoundException {

        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) {
            throw new ClientNotFoundException("Client not found with id: " + clientId);
        }

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBankAccountId(UUID.randomUUID().toString());
        currentAccount.setCreationDate(new Date());
        currentAccount.setBalance(balanceInitial);
        currentAccount.setOverdraft(overdraft);
        currentAccount.setClient(client);

        CurrentAccount currentAccountBBDD = bankAccountRepository.save(currentAccount);

        return bankAccountMapper.currentAccountToDto(currentAccountBBDD);
    }

    @Override
    public SavingAccountDto saveBankSavingAccount(double balanceInitial, double interestRate, Long clientId) throws ClientNotFoundException {

        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) {
            throw new ClientNotFoundException("Client not found with id: " + clientId);
        }

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBankAccountId(UUID.randomUUID().toString());
        savingAccount.setCreationDate(new Date());
        savingAccount.setBalance(balanceInitial);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setClient(client);

        SavingAccount savingAccountBBDD = bankAccountRepository.save(savingAccount);

        return bankAccountMapper.savingAccountToDto(savingAccountBBDD);

    }

    @Override
    public List<ClientDto> getClients() {

        List<Client> clients = clientRepository.findAll();

        List<ClientDto> clientDtos = clients.stream()
                .map(client -> bankAccountMapper.clientToDto(client))
                .toList();

        return clientDtos;
    }

    @Override
    public BankAccountDto getBankAccount(String bankAccountId) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found with id: " + bankAccountId));

        if (bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankAccountMapper.savingAccountToDto(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return bankAccountMapper.currentAccountToDto(currentAccount);
        }
    }

    @Override
    public void debit(String bankAccountId, double amount, String description) throws BankAccountNotFoundException, InsufficientBalanceException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found with id: " + bankAccountId));

        if (bankAccount.getBalance() < amount) {
            throw new InsufficientBalanceException(" Insufficient Balance");
        }

        TradingAccount tradingAccount = new TradingAccount();
        tradingAccount.setOperationType(OperationType.DEBIT);
        tradingAccount.setAmount(amount);
        tradingAccount.setDescription(description);
        tradingAccount.setTradingDate(new Date());
        tradingAccount.setBankAccount(bankAccount);

        tradingAccountRepository.save(tradingAccount);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void credit(String bankAccountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found with id: " + bankAccountId));

        TradingAccount tradingAccount = new TradingAccount();
        tradingAccount.setOperationType(OperationType.CREDIT);
        tradingAccount.setAmount(amount);
        tradingAccount.setDescription(description);
        tradingAccount.setTradingDate(new Date());
        tradingAccount.setBankAccount(bankAccount);

        tradingAccountRepository.save(tradingAccount);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountOwnerId, String accountDestinationId, double amount) throws BankAccountNotFoundException, InsufficientBalanceException {
        debit(accountOwnerId, amount, "Transfer to : " + accountDestinationId);
        credit(accountDestinationId, amount, "Transfer of :" + accountOwnerId);
    }

    @Override
    public List<BankAccountDto> getBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountDto> bankAccountDtos = bankAccounts.stream()
                .map(bankAccount -> {
                    if (bankAccount instanceof SavingAccount) {
                        SavingAccount savingAccount = (SavingAccount) bankAccount;
                        return bankAccountMapper.savingAccountToDto(savingAccount);
                    } else {
                        CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                        return bankAccountMapper.currentAccountToDto(currentAccount);
                    }
                }).toList();

        return bankAccountDtos;
    }

    @Override
    public List<TradingAccountDto> historyAccounts(String bankAccountId) {
        List<TradingAccount> tradingAccounts = tradingAccountRepository.findByBankAccount_BankAccountId(bankAccountId);
        return tradingAccounts.stream()
                .map(tradingAccount -> bankAccountMapper.tradingAccountToDto(tradingAccount))
                .toList();
    }
}
