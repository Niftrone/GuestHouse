package com.gh.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.gh.service.impl.GuestHouseServiceImpl;
import com.gh.vo.employee.Employee;
import com.gh.vo.employee.Manager;
import com.gh.vo.employee.Staff;
import com.gh.vo.reservation.Reservation;
import com.gh.vo.room.Room;

public class GHTest {

	static GuestHouseServiceImpl service = GuestHouseServiceImpl.getInstace();

	static List<Room> rooms = new ArrayList<>();

	static List<Employee> empls = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException{

		boolean flag = true;
		
		System.out.println("게스트 하우스 관리를 진행합니다.\n");
		while (flag) {
			System.out.println(
				    "1: 직원 추가 2: 방 추가 3: 예약 추가 4: 모든 예약 정보 5: 특정 월 예약 정보 6: 특정 월 매출 " +
				    "\n7: 예약 가능한 방 8: 유지보수 예약 9: 예약 수정" +
				    "\n10: 예약 정보 수정 11: 방 타입 수정 12: 직원 정보 수정 13: 예약 취소 14: 방 삭제 15: 직원 삭제" +
				    "\n 16: 전체 직원 17: 전체 방" +
				    "\n0: 종료");
			switch (scan.nextInt()) {
		    case 1: addEmployee(); break;
		    case 2: addRoom(); break;
		    case 3: addReservation(); break;
		    case 4: getAllReservation(); break;
		    case 5: getReservation(5); break;
		    case 6: getIncome(5); break;
		    case 7: getAvailabRooms(); break;
		    case 8: getMaintenance(); break;
		    case 9: getUpdateReservation(); break;
		    case 10: getUpdateReservation(); break;
		    case 11: getUpdateRoomType(); break;
		    case 12: getUpdateEmployeeInfo(); break;
		    case 13: getCancelReservation(); break;
		    case 14: getRemoveRoom(); break;
		    case 15: getRemoveEmployee(); break;
		    case 16: getAllEmployees(); break;
		    case 17: getAllRooms(); break;
		    case 100 : test(); break;
		
		    case 0:
		        flag = false;
		        System.out.println("종료");
		}
		}
	}

	private static void addEmployee() {

		Manager manager = new Manager(1001, LocalDate.of(2020, 5, 10), "김지훈", "010-1234-5678", 5000000, 1000000, 1);
		Staff staff1 = new Staff(2001, LocalDate.of(2021, 3, 15), "이영희", "010-1111-2222", 3000000,
				Arrays.asList(101, 102));
		Staff staff2 = new Staff(2002, LocalDate.of(2022, 1, 20), "박철수", "010-3333-4444", 3100000,
				Arrays.asList(103, 104));
		Staff staff3 = new Staff(2003, LocalDate.of(2023, 7, 5), "최민수", "010-5555-6666", 3200000, Arrays.asList(105));
		Staff staff4 = new Staff(2004, LocalDate.of(2024, 2, 28), "한가을", "010-7777-8888", 3300000,
				Arrays.asList(106, 107, 108));

		empls.add(manager);
		empls.add(staff1);
		empls.add(staff2);
		empls.add(staff3);
		empls.add(staff4);

		for (Employee e : empls) {
			service.addEmployee(e);
		}
	}

	private static void addRoom() {

		rooms.add(new Room(101, 25, 2, false, "Standard", 80000));
		rooms.add(new Room(102, 30, 3, false, "Deluxe", 100000));
		rooms.add(new Room(103, 20, 2, false, "Standard", 75000));
		rooms.add(new Room(104, 35, 4, false, "Suite", 150000));
		rooms.add(new Room(105, 28, 3, false, "Deluxe", 95000));
		rooms.add(new Room(106, 18, 1, false, "Single", 60000));
		rooms.add(new Room(107, 40, 5, false, "Family", 170000));

		for (Room r : rooms) {
			service.addRoom(r);
		}
	}

	private static void addReservation() {
		List<Reservation> reservations = new ArrayList<>();

		reservations.add(
				new Reservation(1, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 3), rooms.get(0), empls.get(0)));
		reservations.add(
				new Reservation(2, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13), rooms.get(1), empls.get(0)));
		reservations.add(
				new Reservation(3, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13), rooms.get(2), empls.get(0)));
		reservations.add(
				new Reservation(4, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 18), rooms.get(3), empls.get(0)));
		reservations.add(
				new Reservation(5, LocalDate.of(2025, 5, 6), LocalDate.of(2025, 5, 8), rooms.get(0), empls.get(0)));

		for (Reservation r : reservations) {
			service.addReservation(r);
		}
	}

	private static void getAllReservation() {
		List<Reservation> result = service.getReservation();
		for (Reservation r : result) {
			System.out.println(r);
		}
	}

	private static void getReservation(int month) {
		List<Reservation> result = service.getReservation(month);
		for (Reservation r : result) {
			System.out.println(r);
		}
	}

	public static void getIncome(int month) {
		int income = service.getIncome(month);

		System.out.println(month + " 월에 총 매출은 " + income + " 원 입니다.");
	}

	private static void getAvailabRooms() {
		List<Room> rooms = service.getAvailableRooms(LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13));
		for(int i =0; rooms.size() > i; i++) {
			System.out.println(rooms.get(i));
		}
	}
	
	private static void getMaintenance() {
		service.roomMaintenance(101, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13));
	}
	
	private static void getUpdateReservation() {
		Reservation updated = new Reservation(1, LocalDate.of(2025, 5, 2), LocalDate.of(2025, 5, 4), rooms.get(0), empls.get(0));
		service.updateReservation(1, updated);
		System.out.println("예약이 성공적으로 수정되었습니다.");
	}

	private static void getUpdateRoomType() {
		service.updateRoomType(105, "Suite");
		System.out.println("105번 방의 타입이 Suite로 변경되었습니다.");
	}

	private static void getUpdateEmployeeInfo() {
		Staff updated = new Staff(2001, LocalDate.of(2021, 3, 15), "이영희", "010-1111-2222", 3000000, Arrays.asList(101, 102));
		service.updateEmployeeInfo(2001, updated);
		System.out.println("2001번 직원 정보가 수정되었습니다.");
	}

	private static void getCancelReservation() {
		service.cancelReservation(5);
		System.out.println("예약번호 5번 예약이 취소되었습니다.");
	}

	private static void getRemoveRoom() {
		service.removeRoom(107);
		System.out.println("방 번호 107번이 삭제되었습니다.");
	}

	private static void getRemoveEmployee() {
		service.removeEmployee(2003);
		System.out.println("직원 번호 2003번이 삭제되었습니다.");
	}
	private static void getAllEmployees() {
		List<Employee> empl = service.getAllEmployees();
		for(Employee e : empl) {
			System.out.println(e);
		}
	}
	private static void getAllRooms() {
		List<Room> rooms = service.getAllRooms();
		for(Room r : rooms) {
			System.out.println(r);
		}
	}
	private static void test() throws IOException{
		String rootPath = System.getProperty("user.dir");
		System.out.println(rootPath);
		File file = new File(rootPath+"\\file.txt");
		if(!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file,true);
	    System.out.println("사원번호등록 : ");
		int empnum = scan.nextInt();
		System.out.println("입사일 : ");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int day = scan.nextInt();
		LocalDate.of(year, month, day);
		System.out.println("이름 : ");
		String name = scan.next();
		System.out.println("전화번호 : ");
		String phoneNum = scan.next();
		System.out.println("월급 : ");
		int salary=scan.nextInt();
		System.out.println("보너스 : ");
		int bonus = scan.nextInt();
		System.out.println("등급 : ");
		int grade = scan.nextInt();
		Manager manager0 = new Manager(empnum, LocalDate.of(year, month, day), name, phoneNum, salary, bonus, grade);
		System.out.println(manager0);
		fw.write(manager0.toString());
		fw.write("\n");
		fw.flush();
		fw.close();
	}
	
	
}