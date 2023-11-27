package com.olvera.digitalbank.controllers;

import com.olvera.digitalbank.dtos.BankAccountDto;
import com.olvera.digitalbank.dtos.HistoryAccountDto;
import com.olvera.digitalbank.dtos.TradingAccountDto;
import com.olvera.digitalbank.exeptions.BankAccountNotFoundException;
import com.olvera.digitalbank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/bankAccounts/{bankAccountId}")
    public BankAccountDto getBankAccountById(@PathVariable(name = "bankAccountId") String bankAccountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(bankAccountId);
    }

    @GetMapping("/bankAccounts")
    public List<BankAccountDto> getBankAccounts() {
        return bankAccountService.getBankAccounts();
    }

    @GetMapping("/accounts/{bankAccountId}/tradings")
    public List<TradingAccountDto> historyAccounts(@PathVariable(name = "bankAccountId") String bankAccountId) {
        return bankAccountService.historyAccounts(bankAccountId);
    }

    @GetMapping("/accounts/{bankAccountId}/tradingsPage")
    public HistoryAccountDto historyAccountsPage(
            @PathVariable(name = "bankAccountId") String bankAccountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
            ) throws BankAccountNotFoundException {
        return bankAccountService.getHistoryAccount(bankAccountId, page, size);
    }

}
