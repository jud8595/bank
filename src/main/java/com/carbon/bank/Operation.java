package com.carbon.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Operation implements IOperation {

    private final OperationType operationType;
    private final BigDecimal amount;
    private final LocalDateTime date;

    public Operation(OperationType operationType, BigDecimal amount, LocalDateTime date) {
        this.operationType = operationType;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public OperationType getType() {
        return operationType;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return operationType == operation.operationType &&
                Objects.equals(amount, operation.amount) &&
                Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, amount, date);
    }
}
