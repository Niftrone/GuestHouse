package com.gh.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.gh.excption.DuplicateException;
import com.gh.excption.NotFoundException;
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

	public static void main(String[] args) throws IOException {

		boolean flag = true;

		System.out.println("게스트 하우스 관리를 진행합니다.\n");
		while (flag) {

			System.out.println("1: 직원 추가\t2: 방 추가\t3: 예약 추가\n" + "4: 방 할인 적용\t5: 전체 예약 조회\t6: 전체 방 조회\n"
					+ "7: 전체 직원 조회\t8: 특정 월 예약 조회\t9: 예약 가능한 방 조회\n" + "10: 인기타입 조회\t" + "11: 예약 정보 수정\t12: 방 타입 수정\n"
					+ "13: 직원 정보 수정\t" + "14: 특정 월 매출 조회\t15: 유지보수 예약\n" + "16: 예약 취소\t" + "17: 방 삭제\t18: 직원 삭제\n"
					+ "19: 텍스트파일에 데이터추가\n" + "0: 종료");

			switch (scan.nextInt()) {
			case 1:
				addEmployee();
				break;
			case 2:
				addRoom();
				break;
			case 3:
				addReservation();
				break;
			case 4:
				setDiscount();
				break;
			case 5:
				getAllReservation();
				break;
			case 6:
				getAllRooms();
				break;
			case 7:
				getAllEmployees();
				break;
			case 8:
				getReservation(5);
				break;
			case 9:
				getAvailabRooms();
				break;
			case 10:
				getPopularRoomTypes();
				break;
			case 11:
				updateReservation();
				break;
			case 12:
				updateRoomType();
				break;
			case 13:
				updateEmployeeInfo();
				break;
			case 14:
				getIncome(5);
				break;
			case 15:
				setMaintenance();
				break;
			case 16:
				cancelReservation();
				break;
			case 17:
				removeRoom();
				break;
			case 18:
				removeEmployee();
				break;
			case 19:
				addFileWriter();
				break;
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
				new Reservation(2, LocalDate.of(2025, 5, 18), LocalDate.of(2025, 5, 19), rooms.get(1), empls.get(0)));
		reservations.add(
				new Reservation(3, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13), rooms.get(2), empls.get(0)));
		reservations.add(
				new Reservation(4, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 18), rooms.get(3), empls.get(0)));
		reservations.add(
				new Reservation(5, LocalDate.of(2025, 5, 6), LocalDate.of(2025, 5, 8), rooms.get(0), empls.get(0)));
		reservations.add(
				new Reservation(6, LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 22), rooms.get(4), empls.get(0)));
		reservations.add(
				new Reservation(7, LocalDate.of(2025, 5, 25), LocalDate.of(2025, 5, 27), rooms.get(5), empls.get(0)));
		reservations.add(
				new Reservation(8, LocalDate.of(2025, 5, 28), LocalDate.of(2025, 5, 30), rooms.get(6), empls.get(0)));
		reservations.add(
				new Reservation(9, LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 5), rooms.get(1), empls.get(0)));
		reservations.add(
				new Reservation(10, LocalDate.of(2025, 5, 7), LocalDate.of(2025, 5, 9), rooms.get(2), empls.get(0)));
		reservations.add(
				new Reservation(11, LocalDate.of(2025, 5, 11), LocalDate.of(2025, 5, 12), rooms.get(3), empls.get(0)));
		reservations.add(
				new Reservation(12, LocalDate.of(2025, 5, 16), LocalDate.of(2025, 5, 18), rooms.get(4), empls.get(0)));
		reservations.add(
				new Reservation(13, LocalDate.of(2025, 5, 22), LocalDate.of(2025, 5, 24), rooms.get(5), empls.get(0)));
		reservations.add(
				new Reservation(14, LocalDate.of(2025, 5, 26), LocalDate.of(2025, 5, 27), rooms.get(6), empls.get(0)));

		reservations.add(
				new Reservation(15, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 3), rooms.get(0), empls.get(0)));
		reservations.add(
				new Reservation(16, LocalDate.of(2025, 6, 4), LocalDate.of(2025, 6, 6), rooms.get(1), empls.get(0)));
		reservations.add(
				new Reservation(17, LocalDate.of(2025, 6, 7), LocalDate.of(2025, 6, 9), rooms.get(2), empls.get(0)));
		reservations.add(
				new Reservation(18, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 12), rooms.get(3), empls.get(0)));
		reservations.add(
				new Reservation(19, LocalDate.of(2025, 6, 13), LocalDate.of(2025, 6, 15), rooms.get(4), empls.get(0)));
		reservations.add(
				new Reservation(20, LocalDate.of(2025, 6, 16), LocalDate.of(2025, 6, 18), rooms.get(5), empls.get(0)));
		reservations.add(
				new Reservation(21, LocalDate.of(2025, 6, 19), LocalDate.of(2025, 6, 21), rooms.get(6), empls.get(0)));
		reservations.add(
				new Reservation(22, LocalDate.of(2025, 6, 22), LocalDate.of(2025, 6, 24), rooms.get(0), empls.get(0)));
		reservations.add(
				new Reservation(23, LocalDate.of(2025, 6, 25), LocalDate.of(2025, 6, 27), rooms.get(1), empls.get(0)));
		reservations.add(
				new Reservation(24, LocalDate.of(2025, 6, 28), LocalDate.of(2025, 6, 29), rooms.get(2), empls.get(0)));
		reservations.add(
				new Reservation(25, LocalDate.of(2025, 6, 29), LocalDate.of(2025, 6, 30), rooms.get(3), empls.get(0)));
		try {
			for (Reservation r : reservations) {
				service.addReservation(r);
			}
		} catch (DuplicateException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void getAllReservation() {
		List<Reservation> result = service.getReservation();
		try {
			result.sort(Comparator.comparing(Reservation::getCheckIn));

			for (Reservation r : result) {
				System.out.println(r);
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getReservation(int month) {
		List<Reservation> result = service.getReservation(month);
		try {
			result.sort(Comparator.comparing(Reservation::getCheckIn));
			for (Reservation r : result) {
				System.out.println(r);
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getIncome(int month) {
		int income = service.getIncome(month);

		System.out.println(month + " 월에 총 매출은 " + income + " 원 입니다.");
	}

	private static void getAvailabRooms() {
		List<Room> rooms = service.getAvailableRooms(LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13));
		rooms.sort(Comparator.comparing(Room::getRoomNum));
		for (int i = 0; rooms.size() > i; i++) {
			System.out.println(rooms.get(i));
		}
	}

	private static void setMaintenance() {
		service.roomMaintenance(101, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13));
	}

	private static void updateReservation() {
		Reservation updated = new Reservation(1, LocalDate.of(2025, 5, 2), LocalDate.of(2025, 5, 4), rooms.get(0),
				empls.get(0));
		service.updateReservation(1, updated);
		System.out.println("예약이 성공적으로 수정되었습니다.");
	}

	private static void updateRoomType() {
		service.updateRoomType(105, "Suite");
	}

	private static void updateEmployeeInfo() {
		Staff updated = new Staff(2001, LocalDate.of(2021, 3, 15), "이영희", "010-1111-2222", 3000000,
				Arrays.asList(101, 102));
		service.updateEmployeeInfo(2001, updated);
	}

	private static void cancelReservation() {
		service.cancelReservation(5);
	}

	private static void removeRoom() {
		service.removeRoom(107);
	}

	private static void removeEmployee() {
		service.removeEmployee(2003);
	}

	private static void getAllEmployees() {
		try {
			List<Employee> empl = service.getAllEmployees();
			empl.sort(Comparator.comparing(Employee::getEmpNum));
			for (Employee e : empl) {
				System.out.println(e);
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void getAllRooms() {
		try {
			List<Room> rooms = service.getAllRooms();
			rooms.sort(Comparator.comparing(Room::getRoomNum));
			for (Room r : rooms) {
				System.out.println(r);
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getPopularRoomTypes() {
		Map<String, Integer> typeCountMap = service.getPopularRoomTypes();
		List<String> roomTypes = Arrays.asList("Family", "Single", "Deluxe", "Suite", "Standard");
		List<String> sortedTypes = new ArrayList<>(roomTypes);
		sortedTypes.sort((t1, t2) -> typeCountMap.get(t2) - typeCountMap.get(t1));
		System.out.println(sortedTypes.get(0) + " type 객실이 가장 인기가 많습니다.");
	}

	private static void addFileWriter() throws IOException {
		String rootPath = System.getProperty("user.dir");
		System.out.println(rootPath);
		File file = new File(rootPath + "\\GH.txt");
		if (!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file, true);
		System.out.print("사원번호등록 : ");
		int empnum = scan.nextInt();
		System.out.print("입사년도 : ");
		int year = scan.nextInt();
		System.out.print("입사월 : ");
		int month = scan.nextInt();
		System.out.print("입사일 : ");
		int day = scan.nextInt();
		LocalDate.of(year, month, day);
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("전화번호 : ");
		String phoneNum = scan.next();
		System.out.print("월급 : ");
		int salary = scan.nextInt();
		System.out.print("보너스 : ");
		int bonus = scan.nextInt();
		System.out.print("등급 : ");
		int grade = scan.nextInt();
		Manager manager0 = new Manager(empnum, LocalDate.of(year, month, day), name, phoneNum, salary, bonus, grade);
		System.out.print(manager0);
		fw.write(manager0.toString());
		fw.write("\n");
		fw.flush();
		fw.close();
	}

	private static void setDiscount() {
		service.setDiscount(101, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 30), 0.15);
		service.setDiscount(102, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 30), 0.15);
		service.setDiscount(103, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 30), 0.15);
		service.setDiscount(104, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 30), 0.15);
		System.out.println("101 ~ 104 번방이 5월 15 ~ 30일까지 15% 할인합니다.");
	}

}