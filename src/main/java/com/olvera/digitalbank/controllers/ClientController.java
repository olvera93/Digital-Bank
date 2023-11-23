package com.olvera.digitalbank.controllers;

import com.olvera.digitalbank.dtos.ClientDto;
import com.olvera.digitalbank.exeptions.ClientNotFoundException;
import com.olvera.digitalbank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/clients")
    public ClientDto saveClient(@RequestBody ClientDto clientDto) {
        return bankAccountService.saveClient(clientDto);
    }

    @PutMapping("/clients/{clientId}")
    public ClientDto updateClient(@PathVariable(name = "clientId") Long clientId, @RequestBody ClientDto clientDto) {
        clientDto.setClientId(clientId);
        return bankAccountService.updateClient(clientDto);
    }

    @DeleteMapping("/clients/{clientId}")
    public void deleteClient(@PathVariable(name = "clientId")Long clientId) {
        bankAccountService.deleteClient(clientId);
    }

    @GetMapping("/clients")
    public List<ClientDto> getClients() {
        return bankAccountService.getClients();
    }
    @GetMapping("/clients/{clientId}")
    public ClientDto getClient(@PathVariable(name = "clientId") Long clientId) throws ClientNotFoundException {
        return bankAccountService.getClient(clientId);
    }
}
