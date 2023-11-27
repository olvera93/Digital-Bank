package com.olvera.digitalbank.mappers;

import com.olvera.digitalbank.dtos.ClientDto;
import com.olvera.digitalbank.dtos.CurrentAccountDto;
import com.olvera.digitalbank.dtos.SavingAccountDto;
import com.olvera.digitalbank.dtos.TradingAccountDto;
import com.olvera.digitalbank.entities.Client;
import com.olvera.digitalbank.entities.CurrentAccount;
import com.olvera.digitalbank.entities.SavingAccount;
import com.olvera.digitalbank.entities.TradingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public ClientDto clientToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);
        return clientDto;
    }

    public Client dtoToClient(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
    }

    public SavingAccountDto savingAccountToDto(SavingAccount savingAccount) {
        SavingAccountDto savingAccountDto = new SavingAccountDto();
        BeanUtils.copyProperties(savingAccount, savingAccountDto);
        savingAccountDto.setClientDto(clientToDto(savingAccount.getClient()));
        savingAccountDto.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDto;
    }

    public SavingAccount dtoToSavingAccount(SavingAccountDto savingAccountDto) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDto, savingAccount);
        savingAccount.setClient(dtoToClient(savingAccountDto.getClientDto()));

        return savingAccount;
    }

    public CurrentAccountDto currentAccountToDto(CurrentAccount currentAccount) {
        CurrentAccountDto currentAccountDto = new CurrentAccountDto();
        BeanUtils.copyProperties(currentAccount, currentAccountDto);
        currentAccountDto.setClientDto(clientToDto(currentAccount.getClient()));
        currentAccountDto.setType(currentAccount.getClass().getSimpleName());

        return currentAccountDto;
    }

    public CurrentAccount dtoToCurrentAccount(CurrentAccountDto currentAccountDto) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDto, currentAccount);
        currentAccount.setClient(dtoToClient(currentAccountDto.getClientDto()));

        return currentAccount;
    }

    public TradingAccountDto tradingAccountToDto(TradingAccount tradingAccount) {
        TradingAccountDto tradingAccountDto = new TradingAccountDto();
        BeanUtils.copyProperties(tradingAccount, tradingAccountDto);
        return tradingAccountDto;
    }
}
