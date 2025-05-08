package com.gh.vo.employee;

import java.time.LocalDate;

public class Employee {
	public static final int empCapacity = 5;
	private int empNum;
	private LocalDate hireDate;
	private String name;
	private String phoneNum;
	private int salary;

	public Employee() {

	}

	public Employee(int empNum, LocalDate hireDate, String name, String phoneNum, int salary) {
		super();
		this.empNum = empNum;
		this.hireDate = hireDate;
		this.name = name;
		this.phoneNum = phoneNum;
		this.salary = salary;
	}

	public int getEmpNum() {
		return empNum;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "empNum=" + empNum + ", hireDate=" + hireDate + ", name=" + name + ", phoneNum=" + phoneNum
				+ ", salary=" + salary;
	}

}
