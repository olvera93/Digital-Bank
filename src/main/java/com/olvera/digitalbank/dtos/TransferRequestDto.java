package com.olvera.digitalbank.dtos;

import lombok.Data;

@Data
public class TransferRequestDto {

    private double amount;

    private String ownerAccount;

    private String recipientAccount;

    private String description;
}
