package com.gh.service;

import com.gh.vo.reservation.Reservation;

public interface ReservationService {
	void cancelReservation(int resNum);

	void addReservation(Reservation reservation);

	void updateReservation(int resNum, Reservation reservation);
}
