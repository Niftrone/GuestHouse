package com.gh.vo.reservation;

import java.time.LocalDate;

import com.gh.vo.employee.Employee;
import com.gh.vo.room.Room;

public class Reservation {
	private int resNum;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Room resRoom;
	private Employee employee;
	private int totalPrice;

	public Reservation() {

	}

	public Reservation(int resNum, LocalDate checkIn, LocalDate checkOut, Room resRoom, Employee employee) {
		super();
		this.resNum = resNum;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.resRoom = resRoom;
		this.employee = employee;
	}

	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
		this.resNum = resNum;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public Room getResRoom() {
		return resRoom;
	}

	public void setResRoom(Room resRoom) {
		this.resRoom = resRoom;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Reservation [resNum=" + resNum + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", resRoom="
				+ resRoom + ", employee=" + employee.getName() + ", totalPrice=" + totalPrice + "]";
	}
	
}
