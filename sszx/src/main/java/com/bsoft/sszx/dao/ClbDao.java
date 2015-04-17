package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class ClbDao {
	
	Session session = null; // Session对象
	Transaction tx = null; // 事物
	
	String fydm = "0";

	public String saveClb(Clb Clb) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.saveOrUpdate(Clb);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Clb> findByZjqd(String id, String fydm) {
		List<Clb> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Clb where id.bh=" + id + " and fydm='" + fydm
					+ "'";

			list = (List<Clb>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public String updateClb(Clb Clb) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.update(Clb);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public List<Clb> findall() {
		List<Clb> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			list = (List<Clb>) session.createQuery("from Clb").list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public String delClb(Clb Clb) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.delete(Clb);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
}
