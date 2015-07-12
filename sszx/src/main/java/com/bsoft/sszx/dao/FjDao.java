package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class FjDao {

	Session session = null; // Session对象
	Transaction tx = null; // 事物

	public List<Fjb> fjlist(String bh, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Fjb where id.fydm='" + fydm + "'"
					+ " and id.bh=" + bh;
			List<Fjb> bm = (List<Fjb>) session.createQuery(sql).list();
			session.getTransaction().commit();

			return bm;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public String saveFjb(Fjb Fjb) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.saveOrUpdate(Fjb);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public int getMaxId(String fydm, String bh) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "select max(id.xh) from Fjb where id.fydm='" + fydm
					+ "'" + " and id.bh=" + bh;
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return (list==null?1:list.get(0)==null?1:(Integer) list.get(0)+1);

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}

	public Fjb findFjb(String bh, String xh, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Fjb where id.fydm='" + fydm + "'"
					+ " and id.bh=" + bh + " and id.xh=" + xh;
			List<Fjb> bm = (List<Fjb>) session.createQuery(sql).list();
			session.getTransaction().commit();
			if (bm.size() > 0)
				return bm.get(0);

		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public String delFj(Fjb Fjb) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.delete(Fjb);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public Fjb findFjbBymc(String mc, String bh, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Fjb where id.fydm='" + fydm + "'"
					+ " and fjdz='" + mc + "' and id.bh=" + bh;
			List<Fjb> bm = (List<Fjb>) session.createQuery(sql).list();
			session.getTransaction().commit();
			if (bm.size() > 0)
				return bm.get(0);
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	
	public Fjb findFjbByFjmc(String fjmc, String bh, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			String sql = "from Fjb where id.fydm='" + fydm + "'"
					+ " and fjmc='" + fjmc + "' and id.bh=" + bh;
			List<Fjb> bm = (List<Fjb>) session.createQuery(sql).list();
			if (bm.size() > 0)
				return bm.get(0);
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	

}
