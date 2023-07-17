package com.webelement.spring.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.webelement.spring.employee.module.Employee;

@Service
public class EmployeeService {

	private static int eid = 0;
	
	private static List<Employee> eList = new ArrayList<>();
	
	static {
		eList.add(new Employee(++eid, "Sankalp", "Nikam", "sankalp@mail.com", "1234567890", "Male", "India", "Thane"));
		eList.add(new Employee(++eid, "Ellyse", "Perry", "eperry@mail.com", "2345678901", "Female", "Australia", "Sydney"));
		eList.add(new Employee(++eid, "Satish", "Manikannan", "satishvk18@mail.com", "3456789012", "Male", "Australia", "Melbourne"));
		eList.add(new Employee(++eid, "Sagar", "Jahagirdar", "ssj08@mail.com", "4567890123", "Male", "India", "Mumbai"));
		eList.add(new Employee(++eid, "Smriti", "Mandanna", "smritim@mail.com", "567890134", "Female", "India", "Mumbai"));
	}
	
	public List<Employee> findByFirstname(String firstname)
	{
		return eList;
	}
	
	public void addEmployee(String firstname, String lastname, String emailid, String mobileno, String gender, String country, String city)
	{
		Employee employee = new Employee(++eid, firstname, lastname, emailid, mobileno, gender, country, city);
		eList.add(employee);
	}

	public void deleteById(int id) {
		Predicate<? super Employee> predicate = employee -> employee.getId() == id;
		eList.removeIf(predicate);
	}
	
	public Employee findById(int id)
	{
		Predicate<? super Employee> predicate = employee -> employee.getId() == id;
		Employee employee = eList.stream().filter(predicate).findFirst().get();
		return employee;
	}

	public void updateEmployee(Employee employee) {
		deleteById(employee.getId());
		eList.add(employee);
		
	}
}
