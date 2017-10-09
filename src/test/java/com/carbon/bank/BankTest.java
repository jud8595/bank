package com.carbon.bank;

import com.carbon.bank.repository.IOperationRepository;
import com.carbon.bank.service.AccountService;
import com.carbon.bank.service.AccountStatementService;
import com.carbon.bank.service.IDateService;
import com.carbon.bank.service.StatementPrintingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankTest {
	
	private AccountService accountService;
	
	@Mock
	private IDateService dateService;

	@Spy
	private IOperationRepository inMemoryOperationRepository = new InMemoryOperationRepository();

	private ArgumentCaptor<IOperation> operationCaptor = ArgumentCaptor.forClass(IOperation.class);
	private LocalDateTime fakeDate = LocalDateTime.of(2020, Month.JUNE, 7, 15, 30);
	
	@BeforeEach
	public void setUp() {
		accountService = new AccountService(
				dateService,
				new StatementPrintingService(),
				new AccountStatementService(),
				inMemoryOperationRepository);
	}

	@Test
	public void when_making_10_deposit_should_balance_be_10() throws AccountException {
		// given
		doReturn(fakeDate).when(dateService).getDate();
		// when
		accountService.deposit(BigDecimal.TEN);
		// then
		verify(inMemoryOperationRepository, Mockito.times(1)).save(operationCaptor.capture());
		IOperation value = operationCaptor.getValue();
		assertThat(value).isEqualTo(new Operation(IOperation.OperationType.DEPOSIT, BigDecimal.TEN, fakeDate));
		verifyNoMoreInteractions(inMemoryOperationRepository);
	}
	
	@Test
	public void when_making_negative_50_deposit_should_throw_AccountException() {
		assertThatThrownBy(() -> accountService.deposit(BigDecimal.valueOf(50).negate()))
				.isInstanceOf(AccountException.class);
	}
	
	@Test
	public void when_making_50_withdrawal_should_balance_be_negative_50() throws AccountException {
		// given
		doReturn(fakeDate).when(dateService).getDate();
		// when
		accountService.withdraw(BigDecimal.valueOf(50));
		// then
		verify(inMemoryOperationRepository, Mockito.times(1)).save(operationCaptor.capture());
		IOperation value = operationCaptor.getValue();
		assertThat(value).isEqualTo(new Operation(IOperation.OperationType.WITHDRAWAL, BigDecimal.valueOf(50), fakeDate));
		verifyNoMoreInteractions(inMemoryOperationRepository);
	}
	
	@Test
	public void when_making_negative_300_withdrawal_should_throw_AccountException() {
		assertThatThrownBy(() -> accountService.withdraw(BigDecimal.valueOf(300).negate()))
				.isInstanceOf(AccountException.class);
	}
	
	@Test
	public void when_printing_account_statement_should_return_formatted_output() throws AccountException {
		// given
		doReturn(fakeDate).when(dateService).getDate();
		accountService.deposit(BigDecimal.valueOf(500));
		accountService.withdraw(BigDecimal.valueOf(200));

		// when
		String accountStatement = accountService.printAccountStatement();

		// then
		final String row_sep = System.lineSeparator();
		String expected = "operation;date;amount;balance" + row_sep + "DEPOSIT;2020/06/07 15:30;500;500" + row_sep + "WITHDRAWAL;2020/06/07 15:30;200;300";
		assertThat(accountStatement).isEqualTo(expected);
	}
}
