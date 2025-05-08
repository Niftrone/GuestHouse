package com.gh.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		} else {
			System.out.println("직원 최대 고용");
			return;
		}

		employees.add(employee);
		System.out.println("직원 등록 완료");
	}

	@Override
	public void addReservation(Reservation reservation) {
		
		if (isRoomUnderMaintenance(reservation.getResRoom(), reservation.getCheckIn(), reservation.getCheckOut())) {
			System.out.println("해당 방은 공사중입니다.");
			return;
		}
		
		for (Reservation r : reservations) {
			if (r.getResNum() == reservation.getResNum()) {
				System.out.println("접수 완료된 예약입니다.");
				return;
			}

			if (isDateOverlap(reservation.getResRoom().getRoomNum(), reservation.getCheckIn(),
					reservation.getCheckOut())) {
				System.out.println("이미 예약된 방입니다.");
				return;
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
		List<Reservation> allRes = getReservation(month);
		int income=0;
		for(Reservation r : allRes) {
			int price =(r.getCheckOut().getDayOfMonth()-r.getCheckIn().getDayOfMonth())*r.getResRoom().getPrice();
			income+=price;
		}
		return income;
	}

	@Override
	public List<String> getPopularRoomTypes() {
		//type Family,Single,Deluxe,Suite,Standard
	    List<Reservation> allRes = getReservation();
	    
	    // 타입별 예약 수를 저장할 Map
	    Map<String, Integer> typeCountMap = new HashMap<>();

	    // 타입 리스트 미리 정의
	    List<String> roomTypes = Arrays.asList("Family", "Single", "Deluxe", "Suite", "Standard");

	    // 초기화
	    for (String type : roomTypes) {
	        typeCountMap.put(type, 0);
	    }

	    // 예약 수 집계
	    for (Reservation r : allRes) {
	        String type = r.getResRoom().getType();
	        typeCountMap.put(type, typeCountMap.getOrDefault(type, 0) + 1);
	    }

	    // 정렬된 타입 리스트 생성 (예약 많은 순)
	    List<String> sortedTypes = new ArrayList<>(roomTypes);
	    sortedTypes.sort((t1, t2) -> typeCountMap.get(t2) - typeCountMap.get(t1));

	    return sortedTypes;
	}

	@Override
	public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//모든 예약 목록을 가져오는 메소드
	@Override
	public List<Reservation> getReservation() {
		return reservations;
	}
	
	//모든 예약 목록 중 특별한(체크인기준) 달에 해당하는 예약목록을 가져오는 메소드
	@Override
	public List<Reservation> getReservation(int month) {
		List<Reservation> result = new ArrayList<>();
		for(Reservation r : reservations) {
			if(r.getCheckIn().getMonthValue()==month) {
				result.add(r);
			}else {
				System.out.println(month+" 월에 해당하는 예약이 없습니다.");
			}
		}
		return result;
	}

	/**
	 * U
	 */
	@Override
	public void roomMaintenance(int roomNum, LocalDate start, LocalDate end) {
		for (Room r : rooms) {
			if (r.getRoomNum() == roomNum) {
				r.setStatus(true);
				r.setMaintenanceStart(start);
				r.setMaintenanceEnd(end);
				System.out.println(roomNum + " 방이 " + start + " ~ " + end + "까지 수리합니다.");
				return;
			}
		}
		System.out.println("해당 방의 번호가 없습니다.");
	}

	@Override
	public void updateReservation(int resNum, Reservation reservation) {
		if (reservations == null) {
			System.out.println("예약이 비어있습니다.");
			return;
		}

		if (isRoomUnderMaintenance(reservation.getResRoom(), reservation.getCheckIn(), reservation.getCheckOut())) {
			System.out.println("해당 방은 공사중입니다.");
			return;
		}
		
		int count = 0;
		for (Reservation r : reservations) {

			if (isDateOverlap(reservation.getResRoom().getRoomNum(), reservation.getCheckIn(),
					reservation.getCheckOut())) {
				System.out.println("예약이 가능하지 않은 방입니다.");
			}

			if (r.getResNum() == resNum) {
				reservations.set(count, reservation);
				System.out.println("예약 정보가 업데이트되었습니다: " + reservation.getResNum());
				return;
			}

			count++;
		}

	}

	@Override
	public void updateRoomType(int roomNum, String newType) {
		if (rooms == null) {
			System.out.println("방이 없습니다");
			return;
		}

		for (Room r : rooms) {
			if (r.getRoomNum() == roomNum) {
				System.out.println(roomNum + "번 방이 변경 되었습니다.");
				r.setType(newType);
				return;
			}
		}

	}

	@Override
	public void updateEmployeeInfo(int empNum, Employee employee) {
		if (employees == null) {
			System.out.println("직원이 없습니다");
			return;
		}

		for (int i = 0; employees.size() > i; i++) {
			if (rooms.get(i).getRoomNum() == empNum) {
				employees.set(i, employee);
				System.out.println("직원 정보가 업데이트되었습니다: " + employee.getName());
				return;
			}
		}
		System.out.println("그런 직원은 찾을 수 없습니다.");
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
		if(find==false) System.out.println("해당 예약건이 존재하지 않습니다.");
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

	private boolean isRoomUnderMaintenance(Room room, LocalDate start, LocalDate end) {
		if (room == null || room.getMaintenanceStart() == null || room.getMaintenanceEnd() == null) {
			return false; // 공사 정보가 없으면 공사 아님
		}
		// 예약 기간과 공사 기간 겹치는지 확인
		return !start.isBefore(room.getMaintenanceStart()) && !end.isAfter(room.getMaintenanceEnd());
	}

	private boolean isDateOverlap(int roomNum, LocalDate start, LocalDate end) {
		for (Reservation r : reservations) {
			if (r.getResRoom().getRoomNum() == roomNum) {
				if (r.getCheckIn().isBefore(end) && r.getCheckOut().isAfter(start)) {
					return true;
				}
			}
		}
		return false;
	}

}
