package com.gh.vo.employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Staff extends Employee {
	private List<Integer> roomNums;

	public Staff() {
		super();
	}

	public Staff(int empNum, LocalDate hireDate, String name, String phoneNum, int salary, List<Integer> roomNums) {
		super(empNum, hireDate, name, phoneNum, salary);
		this.roomNums = roomNums;
	}

	public List<Integer> getRoomNums() {
		return roomNums;
	}

	public void setRoomNums(List<Integer> roomNums) {
		this.roomNums = roomNums;
	}

	@Override
	public String toString() {
		return "Staff [" + super.toString() + "roomNums=" + roomNums + "]";
	}
	
}
