package com.carbon.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StatementItem implements IStatementItem {

    private final IOperation operation;
    private final BigDecimal balance;

    public StatementItem(IOperation operation, BigDecimal balance) {
        this.operation = operation;
        this.balance = balance;
    }

    @Override
    public IOperation.OperationType getType() {
        return operation.getType();
    }

    @Override
    public BigDecimal getAmount() {
        return operation.getAmount();
    }

    @Override
    public LocalDateTime getDate() {
        return operation.getDate();
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}
