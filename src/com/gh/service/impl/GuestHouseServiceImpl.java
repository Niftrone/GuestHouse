package com.gh.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gh.service.EmployeeService;
import com.gh.service.GuestHouseService;
import com.gh.service.RoomService;
import com.gh.vo.employee.Employee;
import com.gh.vo.reservation.Reservation;
import com.gh.vo.room.Room;

public class GuestHouseServiceImpl implements GuestHouseService, EmployeeService, RoomService {

	static private GuestHouseServiceImpl service = new GuestHouseServiceImpl();

	private List<Employee> employees;
	private List<Room> rooms;
	private List<Reservation> reservations;

	private int empCapacity = Employee.empCapacity;
	private int empCount;

	private GuestHouseServiceImpl() {
	    employees = new ArrayList<>();
	    rooms = new ArrayList<>();
	    reservations = new ArrayList<>();
	}

	public static GuestHouseServiceImpl getInstace() {
		return service;
	}

	/**
	 * C
	 */

	@Override
	public void addRoom(Room room) {
		for (Room r : rooms) {
			if (r.getRoomNum() == room.getRoomNum()) {
				System.out.println("이미 존재한 방입니다.");
				return;
			} 
		}

		System.out.println("방 등록 완료");
		rooms.add(room);
	}

	@Override
	public void addEmployee(Employee employee) {
		if (empCapacity >= empCount) {
			for (Employee e : employees) {
				if (e.getEmpNum() == employee.getEmpNum()) {
					System.out.println("이미 존재한 직원입니다.");
					return;
				}
			}
		}else {
			System.out.println("직원 최대 고용");
			return;
		}

		employees.add(employee);
		System.out.println("직원 등록 완료");
	}

	@Override
	public void addReservation(Reservation reservation) {
		for (Reservation r : reservations) {
			if (r.getResNum() == reservation.getResNum()) {
				System.out.println("접수 완료된 예약입니다.");
				return;
			}
			
			if (r.getResRoom().getRoomNum() == reservation.getResRoom().getRoomNum()) {
				if (r.getCheckIn().isBefore(reservation.getCheckOut())
						&& r.getCheckOut().isAfter(reservation.getCheckIn())) {
					System.out.println("이미 예약된 방입니다.");
					return;
				}
			} 
		}
		
		reservations.add(reservation);
		System.out.println("예약 완료되었습니다.");
		return;
	}

	/**
	 * R
	 */
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
	public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
		// TODO Auto-generated method stub
		return null;
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

	/**
	 * U
	 */
	@Override
	public void roomMaintenance(int roomNum, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReservation(int resNum, Reservation reservation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRoomType(int roomNum, String newType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmployeeInfo(int empNum, Employee employee) {
		// TODO Auto-generated method stub

	}

	/**
	 * D
	 */

	@Override
	public void cancelReservation(int resNum) {
		boolean find = false;
		for(Reservation r : reservations) {
			if(r.getResNum()==resNum) {
				find = true;
				System.out.println(r.getResNum()+" 예약을 취소합니다.");
				reservations.remove(r);
				break;
			}
		}
		if(find==false) System.out.println("해당 예약이 존재하지 않습니다.");
	}

	@Override
	public void removeRoom(int roomNum) {
		boolean find = false;
		for(Room r : rooms) {
			if(r.getRoomNum()==roomNum) {
				find = true;
				System.out.println(r.getRoomNum()+" 방을 삭제합니다.");
				reservations.remove(r);
				break;
			}
		}
		if(find==false) System.out.println("삭제하려는 방이 존재하지 않습니다.");
	}

	@Override
	public void removeEmployee(int empNum) {
		boolean find = false;
		for(Employee e : employees) {
			if(e.getEmpNum()==empNum) {
				find = true;
				System.out.println(e.getEmpNum()+" 직원을 삭제합니다.");
				reservations.remove(e);
				break;
			}
		}
		if(find==false) System.out.println("해당 직원이 존재하지 않습니다.");
	}

	@Override
	public void setDiscount(int roomNum, LocalDate start, LocalDate end, double discountRate) {
		for(Room r: rooms) {
			if(r.getRoomNum()==roomNum) {
				
			}
		}
	}

}
