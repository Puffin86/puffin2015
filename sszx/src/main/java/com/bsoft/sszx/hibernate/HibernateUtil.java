package com.bsoft.sszx.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Configures and provides access to Hibernate sessions, tied to the current
 * thread of execution. Follows the Thread Local Session pattern, see
 * {@link http://hibernate.org/42.html }.
 */
public class HibernateUtil {

	static SessionFactory sessionFactory;
	private Session session = null;
	private Transaction tx = null;

	static {
		try {
			Configuration config = new Configuration()
					.configure("/hibernate.cfg.xml");
			sessionFactory = config.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("static块出现错误:" + e.getMessage());
		}
	}

	public void openSession() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}

	public void closeSession() {
		session.close();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

}