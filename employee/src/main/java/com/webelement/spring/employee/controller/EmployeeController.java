package com.webelement.spring.employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.webelement.spring.employee.module.Employee;
import com.webelement.spring.employee.repository.EmployeeRepository;
import com.webelement.spring.employee.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;
	
	private EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository;
	}
	
	@RequestMapping("employee-list")
	public String listAllEmployees(ModelMap map)
	{
		List<Employee> employees = employeeRepository.findByFirstname("Sankalp");
		map.addAttribute("employees", employees);
		return "employeelist";
	}
	
	@RequestMapping(value = "add-employee", method = RequestMethod.GET)
	public String showNewEmployeePage(ModelMap model)
	{
		Employee employee = new Employee(0, "", "", "", "", "", "", "");
		model.put("employee", employee);
		return "employee";
	}
	
	@RequestMapping(value = "add-employee", method = RequestMethod.POST)
	public String addNewEmployeePage(Employee employee)
	{
		employeeRepository.save(employee);
		return "redirect:employee-list";
	}
	
	@RequestMapping("delete-employee")
	public String deleteTodo(@RequestParam int id)
	{
		employeeRepository.deleteById(id);
		return "redirect:employee-list";
	}
	
	@RequestMapping(value = "update-employee", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id ,ModelMap model)
	{
		Employee employee = employeeRepository.findById(id).get();
		model.addAttribute("employee", employee);
		return "employee";
	}
	
	@RequestMapping(value = "update-employee", method = RequestMethod.POST)
	public String updateTodo(Employee employee)
	{
		employeeRepository.save(employee);
		return "redirect:employee-list";
	}
}
