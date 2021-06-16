package com.ashok.reports.dao;

import java.util.List;

import com.ashok.reports.model.Employee;


public interface EmployeeDao {
	
	   long save(Employee emp);

	   Employee get(long id);

	   List<Employee> list();

	   void update(long id, Employee emp);

	   void delete(long id);

}
