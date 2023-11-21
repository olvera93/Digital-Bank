package com.olvera.digitalbank;

import com.olvera.digitalbank.entities.Client;
import com.olvera.digitalbank.entities.CurrentAccount;
import com.olvera.digitalbank.entities.SavingAccount;
import com.olvera.digitalbank.entities.TradingAccount;
import com.olvera.digitalbank.enums.AccountStatement;
import com.olvera.digitalbank.enums.OperationType;
import com.olvera.digitalbank.repositories.BankAccountRepository;
import com.olvera.digitalbank.repositories.ClientRepository;
import com.olvera.digitalbank.repositories.TradingAccountRepository;
import com.olvera.digitalbank.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(BankService bankService) {
		return args -> {
			bankService.consult();
		};
	}

	CommandLineRunner start(ClientRepository clientRepository, BankAccountRepository bankAccountRepository, TradingAccountRepository tradingAccountRepository) {
		return args -> {
			Stream.of("Gonzalo", "Juan", "Victor", "Pedro").forEach(name -> {
				Client client = new Client();
				client.setName(name);
				client.setEmail(name+"@gmail.com");
				clientRepository.save(client);
			});

			clientRepository.findAll().forEach(client -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setBankAccountId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreationDate(new Date());
				currentAccount.setAccountStatement(AccountStatement.CREATED);
				currentAccount.setClient(client);
				currentAccount.setOverdraft(9000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setBankAccountId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreationDate(new Date());
				savingAccount.setAccountStatement(AccountStatement.CREATED);
				savingAccount.setClient(client);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);

			});

			bankAccountRepository.findAll().forEach(bankAccount -> {
				for (int i = 0; i < 10; i++){
					TradingAccount tradingAccount = new TradingAccount();
					tradingAccount.setTradingDate(new Date());
					tradingAccount.setAmount(Math.random()*12000);
					tradingAccount.setOperationType(Math.random() > 0.5 ? OperationType.DEBIT: OperationType.CREDIT);
					tradingAccount.setBankAccount(bankAccount);
					tradingAccountRepository.save(tradingAccount);
				}
			});
		};
	}
}
