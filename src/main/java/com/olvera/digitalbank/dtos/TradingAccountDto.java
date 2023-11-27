package com.olvera.digitalbank.dtos;

import com.olvera.digitalbank.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class TradingAccountDto {

    private Long tradingAccountId;

    private Date tradingDate;

    private double amount;

    private OperationType operationType;

    private String description;

}
