package com.carbon.bank.service;

import com.carbon.bank.AccountException;

import java.math.BigDecimal;

public interface IAccountService {

    void deposit(BigDecimal amount) throws AccountException;

    void withdraw(BigDecimal amount) throws AccountException;

    String printAccountStatement();
}
