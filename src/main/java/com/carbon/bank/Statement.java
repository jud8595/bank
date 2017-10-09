package com.carbon.bank;

import java.util.Collections;
import java.util.List;

public class Statement implements IStatement {

    private final List<IStatementItem> items;

    public Statement(List<IStatementItem> items) {
        this.items = items;
    }

    @Override
    public List<IStatementItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }
}
