package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.clb.Clqd;
import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.hibernate.HibernateUtil;

/**
 * 材料清单
 */
public class ClqdDao {

	Session session = null; // Session对象
	Transaction tx = null; // 事物

	public List<Clqd> findAll() {
		List<Clqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			list = (List<Clqd>) session.createQuery("from Clqd").list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public void save(Clqd clqd) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.save(clqd);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}

}
