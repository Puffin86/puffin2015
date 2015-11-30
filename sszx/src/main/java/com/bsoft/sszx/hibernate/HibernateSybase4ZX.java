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
public class HibernateSybase4ZX {

	static SessionFactory sessionFactoryO;
	private Session session = null;
	private Transaction tx = null;

	static {
		try {
			Configuration config = new Configuration()
					.configure("/Sybase4ZX.cfg.xml");
			sessionFactoryO = config.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Sybase4ZX#static块出现错误:" + e.getMessage());
		}
	}

	public void openSession() {
		session = sessionFactoryO.openSession();
		tx = session.beginTransaction();
	}

	public void closeSession() {
		session.close();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactoryO;
	}

	public static Session getSession() {
		return sessionFactoryO.openSession();
	}

	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
}