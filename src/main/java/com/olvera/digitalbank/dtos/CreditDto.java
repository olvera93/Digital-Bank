package com.olvera.digitalbank.dtos;

import lombok.Data;

@Data
public class CreditDto {

    private String bankAccountId;

    private double amount;

    private String description;

}
