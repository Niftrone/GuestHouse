package com.gh.service;

import com.gh.vo.employee.Employee;

public interface EmployeeService {
	void addEmployee(Employee employee);

	void removeEmployee(int empNum);

	void updateEmployeeInfo(int empNum, Employee employee);
}
