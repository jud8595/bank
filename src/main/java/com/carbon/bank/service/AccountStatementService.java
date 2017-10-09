package com.carbon.bank.service;

import com.carbon.bank.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountStatementService implements IAccountStatementService {

    @Override
    public List<IStatementItem> getAccountStatementItems(List<IOperation> operations) {
        List<IStatementItem> balancedOperations = new ArrayList<>();
        BigDecimal balance = BigDecimal.ZERO;
        for (IOperation op : operations) {
            balance = computeBalance(balance, op);
            balancedOperations.add(new StatementItem(op, balance));
        }
        return balancedOperations;
    }

    private BigDecimal computeBalance(BigDecimal balance, IOperation op) {
        if (op.getType() == IOperation.OperationType.DEPOSIT) {
           return balance.add(op.getAmount());
        } else if (op.getType() == IOperation.OperationType.WITHDRAWAL) {
            return balance.add(op.getAmount().negate());
        } else {
            throw new IllegalArgumentException("Operation type unknown: " + op.getType());
        }
    }

    @Override
    public IStatement getAccountStatement(List<IOperation> operations) {
        return new Statement(this.getAccountStatementItems(operations));
    }
}
