package com.carbon.bank;

import com.carbon.bank.repository.IOperationRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryOperationRepository implements IOperationRepository {

	private final List<IOperation> items = new ArrayList<>();

	@Override
	public List<IOperation> findAll() {
		return Collections.unmodifiableList(this.items);
	}

	@Override
	public boolean save(IOperation operation) {
		return this.items.add(operation);
	}
}
