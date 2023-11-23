package com.olvera.digitalbank.controllers;

import com.olvera.digitalbank.dtos.BankAccountDto;
import com.olvera.digitalbank.exeptions.BankAccountNotFoundException;
import com.olvera.digitalbank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
