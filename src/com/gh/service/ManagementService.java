package com.gh.service;

import java.time.LocalDate;
import java.util.List;

public interface ManagementService {
	int getIncome(int month);

	List<String> getPopularRoomTypes();

	void roomMaintenance(int roomNum, LocalDate start, LocalDate end);

	void setDiscount(int roomNum, LocalDate start, LocalDate end, double discountRate);
}
