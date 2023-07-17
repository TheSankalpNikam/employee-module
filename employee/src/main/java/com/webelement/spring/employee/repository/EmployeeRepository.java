package com.webelement.spring.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webelement.spring.employee.module.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findByFirstname(String firstname);
	
}
