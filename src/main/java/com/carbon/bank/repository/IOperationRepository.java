package com.carbon.bank.repository;

import com.carbon.bank.IOperation;

import java.util.List;

public interface IOperationRepository {

    List<IOperation> findAll();

    boolean save(IOperation operation);
}
