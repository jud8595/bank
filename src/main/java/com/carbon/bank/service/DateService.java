package com.carbon.bank.service;

import java.time.LocalDateTime;

public class DateService implements IDateService {

	public LocalDateTime getDate() {
		return LocalDateTime.now();
	}
}
