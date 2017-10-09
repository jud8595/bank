package com.carbon.bank.service;

import com.carbon.bank.IOperation;
import com.carbon.bank.IStatement;
import com.carbon.bank.IStatementItem;

import java.util.List;

public interface IAccountStatementService {

    List<IStatementItem> getAccountStatementItems(List<IOperation> operations);

    IStatement getAccountStatement(List<IOperation> operations);
}
