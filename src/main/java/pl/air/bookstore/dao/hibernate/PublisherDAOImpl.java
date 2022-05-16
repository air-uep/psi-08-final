package pl.air.bookstore.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.bookstore.dao.*;
import pl.air.bookstore.model.*;
import pl.air.bookstore.service.HibernateService;

public class PublisherDAOImpl implements PublisherDAO {
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	/* create */
	@Override
	public Long save(Publisher object) {
		Session session = getSession();
		session.persist(object);
		Long id = object.getId();
		return id;
	}

	
	/* read */
	@Override
	public Publisher findById(Long id) {
		return getSession().get(Publisher.class, id);
	}

	@Override
	public List<Publisher> findAll() {
		Session session = getSession();
		Query<Publisher> query = session.createQuery("select p from Publisher p", Publisher.class);
		List<Publisher> all = query.getResultList();
		return all;
	}

	/* update */
	@Override
	public void update(Publisher object) {
		Session session = getSession();
		session.merge(object);
	}

	/* delete */
	@Override
	public void delete(Publisher object) {
		Session session = getSession();
		session.remove(object);
	}

}
