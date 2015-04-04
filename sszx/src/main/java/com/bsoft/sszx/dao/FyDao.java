package com.bsoft.sszx.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.user.Fyb;
import com.bsoft.sszx.hibernate.HibernateUtil;

/**
 * 法院
 * 
 * @author Administrator
 */
public class FyDao {
	
	Session session = null; // Session对象
	Transaction tx = null; // 事物

	public String fymc(String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Fyb where fydm='" + fydm + "'";
			Fyb bm = (Fyb) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();

			return bm.getFymc();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public String fydm(String fymc) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Fyb where fymc='" + fymc + "'";
			Fyb bm = (Fyb) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();

			return bm.getFydm();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
}
