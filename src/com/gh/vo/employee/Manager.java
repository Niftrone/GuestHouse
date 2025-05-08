package com.gh.vo.employee;

import java.time.LocalDate;

public class Manager extends Employee {
	private int bonus;
	private int grade;

	public Manager() {
		super();
	}

	public Manager(int empNum, LocalDate hireDate, String name, String phoneNum, int salary, int bonus, int grade) {
		super(empNum, hireDate, name, phoneNum, salary);
		this.bonus = bonus;
		this.grade = grade;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Manager  [" + super.toString() + "bonus=" + bonus + ", grade=" + grade + "]";
	}

}
