package com.olvera.digitalbank.controllers;

import com.olvera.digitalbank.entities.Client;
import com.olvera.digitalbank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/clients")
    public List<Client> getClients() {
        return bankAccountService.getClients();
    }
}
