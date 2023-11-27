package com.olvera.digitalbank.dtos;

import lombok.Data;

@Data
public class DebitDto {

    private String bankAccountId;

    private double amount;

    private String description;

}
