package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;

import com.example.demo.service.EmployeeService;



@Controller
@RequestMapping("/")
public class AuthController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView model) {
		model.setViewName("/login");
		return model;
	}
	
	@GetMapping("/register")
	public String Register() {
		Employee emp = new Employee();
		emp.setUserName("Nhan");
		emp.setPassword("123");
		emp.setAddress("thu duc");
		emp.setFullName("Le Thien Nhan");
		emp.setSalary(12000);
		employeeService.create(emp);
		return "redirect:/login?success=1";
	}
}
