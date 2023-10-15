package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	// create
	public Employee create(Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		employeeRepository.save(emp);
		return emp;
	}

	// list
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Get Employee By Code
	public Employee getEmployeeByCode(String code) {
		Employee employeeDetail = employeeRepository.findByUserName(code);
		return employeeDetail;

	}

	// Update Employee
	public Employee updateEmployee(String code, Employee emp) {
		Employee employeeDetail = employeeRepository.findByUserName(code);
		if (!employeeDetail.equals(null)) {
			employeeRepository.save(emp);
		}
		return emp;
	}

	// Delete Employee
	public boolean deleteEmployee(String code) {
		Employee employeeDetail = employeeRepository.findByUserName(code);
		if (!employeeDetail.equals(null)) {
			employeeRepository.delete(employeeDetail);
		}
		return true;
	}
}
