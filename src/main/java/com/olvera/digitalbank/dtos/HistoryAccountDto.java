package com.olvera.digitalbank.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoryAccountDto {

    private String bankAccountId;

    private double balance;

    private int currentPage;

    private int totalPages;

    private int pageSize;

    private List<TradingAccountDto> tradingAccount;

}
