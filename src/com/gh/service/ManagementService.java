package com.gh.service;

import java.time.LocalDate;
import java.util.Map;

public interface ManagementService {
	int getIncome(int month);

	Map<String, Integer> getPopularRoomTypes();

	void roomMaintenance(int roomNum, LocalDate start, LocalDate end);

	void setDiscount(int roomNum, LocalDate start, LocalDate end, double discountRate);
}
