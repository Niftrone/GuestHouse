package com.gh.test;

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

	public static void main(String[] args) {

		boolean flag = true;
		Scanner scan = new Scanner(System.in);

		System.out.println("게스트 하우스 관리를 진행합니다.\n");
		while (flag) {
			System.out.println("1: 직원 추가 2: 방 추가 3: 예약 추가 0: 종료");
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
					getAllReservation();
					break;
				case 5:
					getReservation(5);
					break;
				case 6:
					getIncome(5);
					break;
				case 7:
					
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

		reservations.add(new Reservation(1, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 3), rooms.get(0), empls.get(0)));
		reservations.add(new Reservation(2, LocalDate.of(2025, 5, 4), LocalDate.of(2025, 5, 6), rooms.get(1), empls.get(0)));
		reservations
				.add(new Reservation(3, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 13), rooms.get(2), empls.get(0)));
		reservations
				.add(new Reservation(4, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 18), rooms.get(3), empls.get(0)));
		reservations.add(new Reservation(5, LocalDate.of(2025, 5, 6), LocalDate.of(2025, 5, 8), rooms.get(0), empls.get(0)));

		for (Reservation r : reservations) {
			service.addReservation(r);
		}
	}
	
	private static void getAllReservation(){
		List<Reservation> result = service.getReservation();
		for(Reservation r : result) {
			System.out.println(r);
		}
	}
	private static void getReservation(int month) {
		List<Reservation> result = service.getReservation(month);
		for(Reservation r : result) {
			System.out.println(r);
		}
	}
	public static void getIncome(int month) {
		int income = service.getIncome(month);
		
		System.out.println(month+" 월에 총 매출은 "+income+" 원 입니다.");
	}
}
