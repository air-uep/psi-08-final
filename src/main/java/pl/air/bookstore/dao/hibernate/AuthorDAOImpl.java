package pl.air.bookstore.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.bookstore.dao.*;
import pl.air.bookstore.model.*;
import pl.air.bookstore.service.HibernateService;

public class AuthorDAOImpl implements AuthorDAO {
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	/* create */
	@Override
	public Long save(Author object) {
		Session session = getSession();
		session.persist(object);
		Long id = object.getId();
		return id;
	}

	
	/* read */
	@Override
	public Author findById(Long id) {
		// wersja długa --> poszczególne polecenia rozpisane "wiersz po wierszu"
		//Session session = getSession();
		//Author one = session.get(Author.class, id);
		//return one;

		// wersja skrócona --> wszystkie polecenia w jednym wierszu
		return getSession().get(Author.class, id);
	}

	@Override
	public Author findByName(String firstName, String lastName) {
		Session session = getSession();
		Query<Author> query = session.createQuery("select a from Author a where a.firstName = :firstName and a.lastName = :lastName", Author.class);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Author one = query.getSingleResult();
		return one;
	}

	@Override
	public List<Author> findAll() {
		Session session = getSession();
		Query<Author> query = session.createQuery("select a from Author a", Author.class);
		List<Author> all = query.getResultList();
		return all;
	}

	/* update */
	@Override
	public void update(Author object) {
		Session session = getSession();
		session.merge(object);
	}

	/* delete */
	@Override
	public void delete(Author object) {
		Session session = getSession();
		session.remove(object);
	}

}
