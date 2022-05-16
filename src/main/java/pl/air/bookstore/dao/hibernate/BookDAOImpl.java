package pl.air.bookstore.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.bookstore.dao.*;
import pl.air.bookstore.model.*;
import pl.air.bookstore.service.HibernateService;

public class BookDAOImpl implements BookDAO {
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	/* create */
	@Override
	public Long save(Book object) {
		Session session = getSession();
		session.persist(object);
		Long id = object.getId();
		return id;
	}

	
	/* read */
	@Override
	public Book findById(Long id) {
		return getSession().get(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		Session session = getSession();
		Query<Book> query = session.createQuery("select b from Book b", Book.class);
		List<Book> all = query.getResultList();
		return all;
	}

	@Override
	public List<Book> findAllByAuthor(Author author) {
		Session session = getSession();
		Query<Book> query = session.createQuery("select b from Book b where :author MEMBER OF b.authors", Book.class);
		query.setParameter("author", author);
		List<Book> all = query.getResultList();
		return all;
	}

	@Override
	public long countByAuthor(Author author) {
		Session session = getSession();
		Query<Long> query = session.createQuery("select COUNT(b) from Book b where :author MEMBER OF b.authors", Long.class);
		query.setParameter("author", author);
		Long count = query.getSingleResult();
		return count;
	}

	@Override
	public long countByPublisher(Publisher publisher) {
		Session session = getSession();
		Query<Long> query = session.createQuery("select COUNT(b) from Book b where b.publisher = :publisher", Long.class);
		query.setParameter("publisher", publisher);
		Long count = query.getSingleResult();
		return count;
	}

	/* update */
	@Override
	public void update(Book object) {
		Session session = getSession();
		session.merge(object);
	}

	/* delete */
	@Override
	public void delete(Book object) {
		Session session = getSession();
		session.remove(object);
	}

	@Override
	public void deleteAllByAuthor(Author author) {
		Session session = getSession();

		List<Book> booksByAuthor = findAllByAuthor(author);
		
		Query<?> query = session.createQuery("delete from Book b where b IN (:booksByAuthor)", Integer.class);
		query.setParameter("booksByAuthor", booksByAuthor);
		query.executeUpdate();
	}

	@Override
	public void deleteAllByPublisher(Publisher publisher) {
		Session session = getSession();
		Query<?> query = session.createQuery("delete from Book where publisher = :publisher", Integer.class);
		query.setParameter("publisher", publisher);
		query.executeUpdate();
	}

}
