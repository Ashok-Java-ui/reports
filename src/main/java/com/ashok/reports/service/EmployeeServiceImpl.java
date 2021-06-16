package com.ashok.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashok.reports.dao.EmployeeDao;
import com.ashok.reports.model.Employee;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao bookDao;

	@Transactional
	@Override
	public long save(Employee book) {
		return bookDao.save(book);
	}

	@Override
	public Employee get(long id) {
		return bookDao.get(id);
	}

	@Override
	public List<Employee> list() {
		return bookDao.list();
	}

	@Transactional
	@Override
	public void update(long id, Employee book) {
		bookDao.update(id, book);
	}

	@Transactional
	@Override
	public void delete(long id) {
		bookDao.delete(id);
	}

}
