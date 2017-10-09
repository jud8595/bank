package com.carbon.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IOperation {

    enum OperationType {DEPOSIT, WITHDRAWAL}

    OperationType getType();

    BigDecimal getAmount();

    LocalDateTime getDate();
}
