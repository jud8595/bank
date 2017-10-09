package com.carbon.bank.service;

import com.carbon.bank.IStatement;
import com.carbon.bank.IStatementItem;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatementPrintingService {

    private final static CharSequence COL_SEP = ";";
    private final static String ROW_SEP = System.lineSeparator();
    private final static List<String> HEADERS = Arrays.asList("operation", "date", "amount", "balance");
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public String print(IStatement statement) {
        List<String> rows = new ArrayList<>(Arrays.asList(getHeader()));
        rows.addAll(getRows(statement));
        return String.join(ROW_SEP, rows);
    }

    private List<String> getRows(IStatement statement) {
        return statement.getItems().stream()
                .map(this::getHistoryItem)
                .collect(Collectors.toList());
    }

    private String getHistoryItem(IStatementItem item) {
        return String.join(COL_SEP,
                Arrays.asList(
                    item.getType().toString(),
                    item.getDate().format(DATE_TIME_FORMATTER),
                    String.valueOf(item.getAmount()),
                    String.valueOf(item.getBalance())));
    }

    private String getHeader() {
        return String.join(COL_SEP, HEADERS);
    }
}
