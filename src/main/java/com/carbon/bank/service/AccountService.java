package com.carbon.bank.service;

import com.carbon.bank.AccountException;
import com.carbon.bank.IOperation;
import com.carbon.bank.IStatement;
import com.carbon.bank.Operation;
import com.carbon.bank.repository.IOperationRepository;

import java.math.BigDecimal;

public class AccountService implements IAccountService {
	
	private final IDateService dateService;
	private final StatementPrintingService statementPrintingService;
	private final IAccountStatementService accountStatementService;
	private final IOperationRepository operationRepository;

	public AccountService(IDateService dateService, StatementPrintingService statementPrintingService, IAccountStatementService accountStatementService, IOperationRepository operationRepository) {
		this.dateService = dateService;
		this.statementPrintingService = statementPrintingService;
		this.accountStatementService = accountStatementService;
		this.operationRepository = operationRepository;
	}

	public void deposit(BigDecimal amount) throws AccountException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new AccountException("amount should not be negative!");
		}
		this.operationRepository.save(
				new Operation(
						IOperation.OperationType.DEPOSIT,
						amount,
						this.dateService.getDate()));
	}

	public void withdraw(BigDecimal amount) throws AccountException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new AccountException("amount should not be negative!");
		}
		this.operationRepository.save(
				new Operation(
						IOperation.OperationType.WITHDRAWAL,
						amount,
						this.dateService.getDate()));
	}

	public String printAccountStatement() {
		return this.statementPrintingService.print(
				accountStatementService.getAccountStatement(operationRepository.findAll()));
	}
}
