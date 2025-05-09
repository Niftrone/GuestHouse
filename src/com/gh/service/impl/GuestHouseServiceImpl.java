package com.gh.service.impl;


/**
 * <pre>
 * {@code
 * 	GuestHouseService 클래스는 GuestHouse 정보를 활용하여 각각의 기능을 구현하는 클래스
 * 	해당클래스에서는 Java Document주석을 달아서 처리했다
 * }
 * </pre>
 * @author 강민기,이우진,함동윤
 * @version project version 1.0
 * @since JDK17
 * 
 */

import java.lang.module.ResolutionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gh.excption.DuplicateException;
import com.gh.excption.NotFoundException;
import com.gh.service.EmployeeService;
import com.gh.service.GuestHouseService;
import com.gh.service.RoomService;
import com.gh.vo.employee.Employee;
import com.gh.vo.reservation.Reservation;
import com.gh.vo.room.Room;

public class GuestHouseServiceImpl implements GuestHouseService, EmployeeService, RoomService {
	
	/**
	 * GuestHouseService를 싱글톤패턴으로 구현
	 */
	static private GuestHouseServiceImpl service = new GuestHouseServiceImpl();
	
	/**
	 * GuestHouse의 직원정보를 담는 List
	 */
	private List<Employee> employees;
	
	/**
	 * GuestHouse의 객실정보를 담는 List
	 */
	private List<Room> rooms;
	
	/**
	 * GuestHouse의 예약정보를 담는 List
	 */
	private List<Reservation> reservations;

	/**
	 * GuestHouse의 할인정보를 담는 Map
	 */
	private Map<Integer, DiscountnInfo> roomDiscount = new HashMap<>();

	/**
	 * GuestHouse의 직원수를 5명으로 제한하기 위한 static 변수
	 */
	private int empCapacity = Employee.empCapacity;
	
	/**
	 * GuestHouse의 직원수 파악하기 위한 변수
	 */
	private int empCount;

	/**
	 * GuestHouse의 기본생성자
	 */
	private GuestHouseServiceImpl() {
		employees = new ArrayList<>();
		rooms = new ArrayList<>();
		reservations = new ArrayList<>();
	}
	/**
	 * GuestHouseService를 싱글톤패턴을 구현하기 위한 public static 메소드
	 */
	public static GuestHouseServiceImpl getInstace() {
		return service;
	}

	/**
	 * 객실정보를 받아서 새로운 객실을 추가하는 기능
	 * @param room 객실정보
	 */
	@Override
	public void addRoom(Room room) throws DuplicateException{
		for (Room r : rooms) {
			if (r.getRoomNum() == room.getRoomNum()) {
				throw new DuplicateException("이미 존재한 객실입니다.");
			}
		}

		System.out.println("객실 등록 완료");
		rooms.add(room);
	}
	
	/**
	 * 직원정보를 받아서 새로운 직원을 추가하는 기능
	 * @param employee 직원정보
	 */
	@Override
	public void addEmployee(Employee employee) throws DuplicateException{
		if (empCapacity >= empCount) {
			for (Employee e : employees) {
				if (e.getEmpNum() == employee.getEmpNum()) {
					throw new DuplicateException("이미 존재한 직원입니다.");
				}
			}
		} else {
			System.out.println("직원 최대 고용");
			return;
		}
		empCount ++;
		employees.add(employee);
		System.out.println("직원 등록 완료");
	}
	
	/**
	 * 예약정보를 받아서 새로운 예약을 추가하는 기능
	 * 예약하려는 날짜에 객실이 공사중 or 이미 예약완료된 객실이면 예약을 막아주는 기능
	 * @param reservation 예약정보
	 */
	@Override
	public void addReservation(Reservation reservation) throws DuplicateException {
		
		if (isRoomUnderMaintenance(reservation.getResRoom(), reservation.getCheckIn(), reservation.getCheckOut())) {
			System.out.println("해당 객실은 공사중입니다.");
			return;
		}

		for (Reservation r : reservations) {
			if (r.getResNum() == reservation.getResNum()) {
				System.out.println("접수 완료된 예약입니다.");
				return;
			}

			if (isDateOverlap(reservation.getResRoom().getRoomNum(), reservation.getCheckIn(),
					reservation.getCheckOut())) {
				throw new DuplicateException("이미 예약된 예약입니다.");
			}
		}
		
		int finalPrice = discountedPrice(reservation);
		reservation.setTotalPrice(finalPrice);

		reservations.add(reservation);
		System.out.println("예약 완료되었습니다.");
		return;
	}

	/**
	 * 모든 직원목록을 반환하는기능
	 * @return List<Employee> 모든 직원목록
	 */

	public List<Employee> getAllEmployees() throws NotFoundException{
		if (employees.isEmpty()) {
			throw new NotFoundException("등록된 직원이 없습니다.");
		}
		return employees;


	}
	
	/**
	 * 모든 객실목록을 반환하는기능
	 * @return List<Room> 모든 객실목록
	 */

	public List<Room> getAllRooms() throws NotFoundException{
		if (rooms.isEmpty()) {
			 throw new NotFoundException("등록된 객실이 없습니다.");
		}
		return rooms;
	}

	/**
	 * 모든 예약목록을 반환하는기능
	 * @return List<Reservation>모든 예약목록
	 */
	@Override
	public List<Reservation> getReservation()throws NotFoundException {
		if(reservations == null) {
			throw new NotFoundException("등록된 예약이 없습니다.");
		}
		return reservations;
	}
	
	/**
	 * 모든 예약목록 중 특정한(체크인기준) 월에 해당하는 예약목록을 반환하는기능
	 * @return List<Reservation>특정한월 기준 예약정보
	 */
	@Override
	public List<Reservation> getReservation(int month) {
		List<Reservation> result = new ArrayList<>();
		for (Reservation r : reservations) {
			if (r.getCheckIn().getMonthValue() == month) {
				result.add(r);
			} else {
				System.out.println(month + " 월에 해당하는 예약이 없습니다.");
			}
		}
		return result;
	}

	/**
	 * 월별 매출금액을 반환하는기능
	 * @param month 조회할 월
	 * @return int 월 매출금액
	 */


	@Override
	public int getIncome(int month) {
		List<Reservation> allRes = getReservation(month);
		int income = 0;
		
		for (Reservation r : allRes) {
			if(r.getTotalPrice() == 0) {
				int finalPrice = discountedPrice(r);
				r.setTotalPrice(finalPrice);
			}
			income += r.getTotalPrice();
		}
		return income;
	}
	
	/**
	 * 인기있는 객실 type을 반환하는기능
	 * @return Map<String, Integer> 객실 type 별 예약건수
	 */
	@Override
	public Map<String, Integer> getPopularRoomTypes() {
		// type Family,Single,Deluxe,Suite,Standard
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

		return typeCountMap;
	}
	
	/**
	 * 조회한 날짜에 예약가능한 객실을 조회하는 기능
	 * @param checkIn 체크인날짜
	 * @param checkOut 체크아웃날짜
	 * @return List<Room> 조회한 날짜에 이용가능한 객실목록
	 */
	@Override
	public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
		List<Room> room = new ArrayList<>();
		
		int count = 0;
		for (Reservation r : reservations) {
			if (!(r.getCheckIn().isBefore(checkOut) && r.getCheckOut().isAfter(checkIn))) {
				room.add(rooms.get(count));
			}
			count++;
		}

		return room;
	}

	/**
	 * 객실 수리를 위한 객실상태를 이용불가로 제한하는 기능
	 * @param roomNum 객실번호
	 * @param start 수리시작날짜
	 * @param end 수리완료날짜
	 */
	@Override
	public void roomMaintenance(int roomNum, LocalDate start, LocalDate end) {
		for (Room r : rooms) {
			if (r.getRoomNum() == roomNum) {
				r.setStatus(true);
				r.setMaintenanceStart(start);
				r.setMaintenanceEnd(end);
				System.out.println(roomNum + " 객실이 " + start + " ~ " + end + "까지 수리합니다.");
				return;
			}
		}
		System.out.println("해당 객실의 번호가 없습니다.");
	}
	
	/**
	 * 예약을 변경하는 기능
	 * 예약변경하려는 날짜에 객실이 공사중 or 이미 예약완료된 객실이면 예약을 막아주는 기능
	 * @param resNum 예약번호
	 * @param reservation 예약정보
	 */
	@Override
	public void updateReservation(int resNum, Reservation reservation) {
		if (reservations == null) {
			System.out.println("예약이 비어있습니다.");
			return;
		}

		if (isRoomUnderMaintenance(reservation.getResRoom(), reservation.getCheckIn(), reservation.getCheckOut())) {
			System.out.println("해당 객실은 공사중입니다.");
			return;
		}

		int count = 0;
		for (Reservation r : reservations) {

			if (isDateOverlap(reservation.getResRoom().getRoomNum(), reservation.getCheckIn(),
					reservation.getCheckOut())) {
				System.out.println("예약이 가능하지 않은 객실입니다.");
			}

			int finalPrice = discountedPrice(reservation);
			reservation.setTotalPrice(finalPrice);
			
			if (r.getResNum() == resNum) {
				reservations.set(count, reservation);
				System.out.println("예약 정보가 업데이트되었습니다: " + reservation.getResNum());
				return;
			}

			count++;
		}

	}
	
	/**
	 * 객실의 type을 변경하는 기능
	 * @param roomNum 객실번호
	 * @param newType 객실타입
	 */
	@Override
	public void updateRoomType(int roomNum, String newType) {
		if (rooms == null) {
			System.out.println("객실이 없습니다");
			return;
		}

		for (Room r : rooms) {
			if (r.getRoomNum() == roomNum) {
				System.out.println(roomNum + "번 객실이 변경 되었습니다.");
				System.out.println("방의 타입이"+newType+" 로 변경되었습니다.");
				r.setType(newType);
				return;
			}
		}

	}
	
	/**
	 * 직원의 정보를 변경하는 기능
	 * @param empNum 객실번호
	 * @param employee 객실타입
	 */
	@Override
	public void updateEmployeeInfo(int empNum, Employee employee) {
		if (employees == null) {
			System.out.println("직원이 없습니다");
			return;
		}

		for (int i = 0; employees.size() > i; i++) {
			if (employees.get(i).getEmpNum() == empNum) {
				employees.set(i, employee);
				System.out.println(employee.getName()+" 직원 정보가 업데이트되었습니다");
				return;
			}
		}
		System.out.println("그런 직원은 찾을 수 없습니다.");
	}

	/**
	 * 예약을 취소하는 기능
	 * @param resNum 예약번호
	 */
	@Override
	public void cancelReservation(int resNum) {
		boolean find = false;
		for (Reservation r : reservations) {
			if (r.getResNum() == resNum) {
				find = true;
				System.out.println(r.getResNum() + " 예약을 취소합니다.");
				reservations.remove(r);
				break;
			}
		}
		if (find == false)
			System.out.println("해당 예약건이 존재하지 않습니다.");
	}
	
	/**
	 * 객실을 삭제하는 기능
	 * @param roomNum 객실번호
	 */
	@Override
	public void removeRoom(int roomNum) {
		boolean find = false;
		for (Room r : rooms) {
			if (r.getRoomNum() == roomNum) {
				find = true;
				System.out.println(r.getRoomNum() + " 객실을 삭제합니다.");
				rooms.remove(r);
				break;
			}
		}
		if (find == false)
			System.out.println("삭제하려는 객실이 존재하지 않습니다.");
	}
	
	/**
	 * 직원을 삭제하는 기능
	 * @param empNum 직원번호
	 */
	@Override
	public void removeEmployee(int empNum) {
		boolean find = false;
		for (Employee e : employees) {
			if (e.getEmpNum() == empNum) {
				find = true;
				System.out.println(e.getEmpNum() + " 직원을 삭제합니다.");
				employees.remove(e);
				break;
			}
		}
		if (find == false)
			System.out.println("해당 직원이 존재하지 않습니다.");
	}
	
	/**
	 * 객실을 일정기간동안 가격할인을 하는기능
	 * @param roomNum 직원번호
	 * @param start 할인시작일
	 * @param end 할인종료일
	 * @param discountRate 할인률
	 */
	@Override
	public void setDiscount(int roomNum, LocalDate start, LocalDate end, double discountRate) {
		boolean flag = true;
		for(Room r : rooms) {
			if(r.getRoomNum() == roomNum) {
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("없는 방번호 입니다.");
			return;
		}
		
		roomDiscount.put(roomNum, new DiscountnInfo(start, end, discountRate));
		System.out.println(roomNum + "방의 할인이 적용되었습니다.");
	}
	
	/**
	 * 할인가격을 적용하는기능
	 * @param re 예약정보
	 * @return int 할인된가격
	 */
	private int discountedPrice(Reservation re) {
		int basePrice = re.getResRoom().getPrice();
		int days = re.getCheckOut().getDayOfMonth() - re.getCheckIn().getDayOfMonth();
		double discount = 0.0;
		
		DiscountnInfo info = roomDiscount.get(re.getResRoom().getRoomNum());
		
		if(info != null) {
			boolean over = !(re.getCheckOut().isBefore(info.start) || re.getCheckIn().isAfter(info.end));
			if(over) {
				discount = info.rate;
			}
		}
		return (int) (basePrice * days * (1 - discount));
	}
	
	/**
	 * 객실의 수리를 하기 위해 객실상태를 변경하는 기능
	 * @param room 객실정보
	 * @param start 수리시작일
	 * @param end 수리종료일
	 */
	private boolean isRoomUnderMaintenance(Room room, LocalDate start, LocalDate end) {
		if (room == null || room.getMaintenanceStart() == null || room.getMaintenanceEnd() == null) {
			return false; // 수리 정보가 없으면 공사 아님
		}
		// 예약 기간과 수리 기간 겹치는지 확인
		return !start.isBefore(room.getMaintenanceStart()) && !end.isAfter(room.getMaintenanceEnd());
	}

	/**

	 * 객실의 중복예약을 방지하는 기능
	 * 백준 1374강의실 문제를 활용한 풀이 예약 시스템에 중복 예약을 방지하는 알고리즘 적용
	 * @param roomNum 객실번호
	 * @param checkIn 체크인날짜
	 * @param checkOut 체크아웃날짜
	 * 백준 1374강의실 문제를 활용한 풀이 예약 시스템에 중복 예약을 방지하는 알고리즘 적용
	 */
	 private boolean isDateOverlap(int roomNum, LocalDate checkIn, LocalDate checkOut) {

		for (Reservation r : reservations) {
			if (r.getResRoom().getRoomNum() == roomNum) {
				if (r.getCheckIn().isBefore(checkOut) && r.getCheckOut().isAfter(checkIn)) {
					return true;
				}
			}
		}
		return false;
	}
	 /**
	  * 할인가격정보를 일시적으로 적용하는 내부 클래스
	  */
	private static class DiscountnInfo {
		LocalDate start;
		LocalDate end;
		double rate;

		DiscountnInfo(LocalDate start, LocalDate end, double rate){
			this.start = start;
			this.end = end;
			this.rate =rate;
		}
	}
}
