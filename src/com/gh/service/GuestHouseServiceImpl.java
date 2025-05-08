package com.gh.service;

import java.time.LocalDate;
import java.util.List;

import com.gh.vo.employee.Employee;
import com.gh.vo.reservation.Reservation;
import com.gh.vo.room.Room;

public class GuestHouseServiceImpl implements GuestHouseService, EmployeeService, RoomService{

	@Override
	public int getIncome(int month) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getPopularRoomTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void roomMaintenance(int roomNum, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDiscount(int roomNum, LocalDate start, LocalDate end, double discountRate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelReservation(int resNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReservation(int resNum, Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRoom(int roomNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRoomType(int roomNum, String newType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEmployee(int empNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployeeInfo(int empNum, Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> getReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservation(int month) {
		// TODO Auto-generated method stub
		return null;
	}

}
