package com.ashok.reports.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashok.reports.model.Employee;

@Repository
public class EmployeeDaoimpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long save(Employee book) {
		sessionFactory.getCurrentSession().save(book);
		return book.getId();
	}

	@Override
	public Employee get(long id) {
		return sessionFactory.getCurrentSession().get(Employee.class, id);
	}

	@Override
	public List<Employee> list() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		Query<Employee> query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void update(long id, Employee book) {
		Session session = sessionFactory.getCurrentSession();
		Employee book2 = session.byId(Employee.class).load(id);
		book2.setName(book.getName());
		book2.setEmail(book.getEmail());
		session.flush();
	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Employee book = session.byId(Employee.class).load(id);
		session.delete(book);
	}

}
