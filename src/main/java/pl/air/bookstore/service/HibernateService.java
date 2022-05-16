package pl.air.bookstore.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Map;
import java.util.Objects;

public class HibernateService {
	
	private static SessionFactory sessionFactory;

	/* session factory */
	public static void startup() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static void shutdown() {
		sessionFactory.close();
	}
	
	/* session */
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static void closeSession() {
		getSession().close();
	}
	
	/* transaction */
	public static Transaction beginTransaction() {
		Transaction tx = getSession().beginTransaction();
		return tx;
	}
	
	public static void commitTransaction() {
		Transaction tx = getSession().getTransaction();
		tx.commit();
	}
	
	public static void rollbackTransaction() {
		Transaction tx = getSession().getTransaction();
		tx.rollback();
	}

	/* config option */
	public static boolean isCreate() {
		Map<String, Object> props = sessionFactory.getProperties();
		String value = (String) props.get("hbm2ddl.auto");
		boolean isCreated = Objects.equals(value, "create");
		return isCreated;
	}

}
