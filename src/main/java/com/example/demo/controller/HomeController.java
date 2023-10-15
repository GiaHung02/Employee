package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	EmployeeService employeeService;

	@GetMapping()
	public ModelAndView home(ModelAndView model) {
		model.setViewName("/create");
		model.addObject("employee", new Employee());
		return model;
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("Employee") Employee emp) {
		employeeService.create(emp);
		return "redirect:/list";
	}

	@GetMapping("/list")
	public ModelAndView listEmployees(ModelAndView model) {
		List<Employee> emp = employeeService.getAllEmployees();
		model.setViewName("list");
		model.addObject("employees", emp);
		return model; // Assuming "employee-list" is the name of your listing page view
	}

	@GetMapping("/update/{userName}")
	// userName is a attribute in a model
	public ModelAndView updateEmployee(ModelAndView model, @PathVariable String userName) {
		model.addObject("employee", employeeService.getEmployeeByCode(userName));
		model.setViewName("update");
		return model;
	}

	// userName is a attribute in a model
	@PostMapping("/update/{userName}")
	public String update(@ModelAttribute Employee emp, Model model, @PathVariable String userName) {
		employeeService.updateEmployee(userName, emp);
		return "redirect:/list";
	}

	// Delete
	@GetMapping("/delete/{userName}")
	public String delete(@PathVariable String userName, Model model) {
		employeeService.deleteEmployee(userName);
		return "redirect:/list";
	}
}
