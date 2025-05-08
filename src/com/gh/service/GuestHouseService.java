package com.gh.service;

import java.util.List;

import com.gh.vo.reservation.Reservation;

public interface GuestHouseService extends ManagementService, ReservationService{
	List<Reservation> getReservation();
	List<Reservation> getReservation(int month);
}
