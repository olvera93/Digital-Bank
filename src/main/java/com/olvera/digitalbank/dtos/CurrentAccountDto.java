package com.olvera.digitalbank.dtos;

import com.olvera.digitalbank.enums.AccountStatement;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentAccountDto extends BankAccountDto {

    private String bankAccountId;

    private double balance;

    private Date creationDate;

    private AccountStatement accountStatement;

    private ClientDto clientDto;

    private double overdraft;

}
