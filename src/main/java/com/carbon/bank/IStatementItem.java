package com.carbon.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IStatementItem {

    IOperation.OperationType getType();

    BigDecimal getAmount();

    LocalDateTime getDate();

    BigDecimal getBalance();
}
