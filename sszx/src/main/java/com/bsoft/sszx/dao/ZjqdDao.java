package com.bsoft.sszx.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class ZjqdDao {

	Session session = null; // Session对象
	Transaction tx = null; // 事物

	public String saveZjqd(Zjqd Zjqd) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.saveOrUpdate(Zjqd);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public int getMaxId(String fydm) {
		int maxid = 0;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			String sql = "select max(id.bh) from Zjqd where id.fydm='" + fydm
					+ "'";
			List list = session.createQuery(sql).list();
			
			if(list==null){
				maxid =  1;
			}else if(list.get(0)==null){
				maxid = 1;
			}else{
				maxid = (Integer) list.get(0) + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return maxid;
	}

	@SuppressWarnings("unchecked")
	public Zjqd findbyid(String id, String fydm) {
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd where id.bh=" + id + " and fydm like '%" + fydm+"%'";
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list.get(0);
	}

	public String updateZjqd(Zjqd Zjqd) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.update(Zjqd);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public List<Zjqd> findall() {
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			list = (List<Zjqd>) session.createQuery("from Zjqd").list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	
	
	public List<Zjqd> findDsrZzYSJ(String user, int zt, String fydm,String lclx) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd where sjr='" + user + "' and zt=" + zt
					+ " and fydm=" + fydm+" and lclx='"+lclx+"'";
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	

	public List<Zjqd> findDsrZzSJ(String user, int zt, String fydm) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd where dqcyr='" + user + "' and zt=" + zt
					+ " and fydm=" + fydm;
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public List<Zjqd> findDsrZzSJ_2(int zt, String fydm) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd where zt=" + zt + " and fydm=" + fydm;
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public List<Zjqd> findDsrZzSJbyPage(int start, int number, String user,
			int zt, String fydm) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd " + "where dqcyr='" + user + "' and zt="
					+ zt + " and id.fydm='" + fydm + "'";
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = query.list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	
	public List<Zjqd> findDsrZzYSJbyPage(int start, int number, String user,
			int zt, String fydm,String lclx,String sort,String order) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd " + "where sjr='" + user + "' and zt="
					+ zt + " and id.fydm='" + fydm + "'" +" and lclx='"+lclx+"'";
			
			if(sort!=null ||!"".equals(sort)){
				sql += "  order by "+sort+" "+order;
			}
			
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = query.list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	
	public List<Zjqd> findDsrZzSJbyPage(int start, int number, String user,
			int zt, String fydm,String sort,String order) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd " + "where dqcyr='" + user + "' and zt="
					+ zt + " and id.fydm='" + fydm + "' order by "+sort+" "+order;
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = query.list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public List<Zjqd> findDsrZzSJbyPage_2(int start, int number, int zt,
			String fydm) {// 查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd " + "where zt=" + zt + " and id.fydm='"
					+ fydm + "'";
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = query.list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public List<Zjqd> findDsrZzSJ_Search(String user, int zt, String dsr,
			String ah, String fydm) {// 按条件查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd where dqcyr='" + user + "' and zt=" + zt
					+ " and djr like '%" + dsr + "%' and ah like '%" + ah
					+ "%'" + " and fydm=" + fydm;
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public List<Zjqd> findDsrZzSJ_Search_2(int zt, String dsr, String ah,
			String fydm) {// 按条件查找当事人主动送件
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "from Zjqd where zt=" + zt + " and djr like '%" + dsr
					+ "%' and ah like '%" + ah + "%'" + " and fydm=" + fydm;
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public String delZjqd(Zjqd Zjqd) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.delete(Zjqd);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	
	public List<Zjqd> findCljlBySQL(String sql,int start,int number) {
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = (List<Zjqd>) query.list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	
	public List<Zjqd> findCljlBySQL(String sql) {
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	
	public List<Zjqd> findCljlTotalBySQL(String sql) {
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("lclx", Hibernate.STRING);
			query.addScalar("zjrXm", Hibernate.STRING);
			query.addScalar("sjrBmmc", Hibernate.STRING);
			query.addScalar("sjrXm", Hibernate.STRING);
			query.addScalar("sl", Hibernate.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(Zjqd.class));
			list = (List<Zjqd>)query.list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}
	
	
	public List<Zjqd> findCljl(String user, String lx, String dsr, String ah,
			String fydm, String userBm) {
		List<Zjqd> list = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction(); // 开启事物
			String sql = "";
			if (lx.equals("2"))
				sql = "from Zjqd where sjr='" + user + "'" + " and djr like '%"
						+ dsr + "%' and ah like '%" + ah + "%'" + " and fydm='"
						+ fydm + "'";
			if (lx.equals("1") || lx.equals("3"))
				sql = "from Zjqd where " + " djr like '%" + dsr
						+ "%' and ah like '%" + ah + "%'" + " and fydm='"
						+ fydm + "'";
			if (lx.equals("4"))
				sql = "from Zjqd where sjrbm='" + userBm + "'"
						+ " and djr like '%" + dsr + "%' and ah like '%" + ah
						+ "%'" + " and fydm='" + fydm + "'";
			list = (List<Zjqd>) session.createQuery(sql).list();
			session.getTransaction().commit();// 提交事物
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return list;
	}

	public int countByZt(String fydm, String user, int zt) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Zjqd where id.fydm='" + fydm + "'"
					+ " and dqcyr='" + user + "' and zt=" + zt;
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return list.size();

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}
	
	public int countjjsx(String fydm, String user,String lclx,String zt) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = " FROM Zjqd WHERE TIMESTAMPDIFF(DAY,CURDATE(),sxsj)<=CEILING(sx/3) and TIMESTAMPDIFF(DAY,CURDATE(),sxsj)>=0 and id.fydm='"+fydm+"' and sjr='"+user+"' and lclx='"+lclx+"' and zt in("+zt+")";
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return list.size();

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}
	
	
	public int countjjsx_fwzx(String fydm, String user,String lclx,String zt) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = " FROM Zjqd WHERE TIMESTAMPDIFF(DAY,CURDATE(),sxsj)<=CEILING(sx/3) and TIMESTAMPDIFF(DAY,CURDATE(),sxsj)>=0 and id.fydm='"+fydm+"'  and lclx='"+lclx+"' and zt in("+zt+")";
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return list.size();

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}
	
	
	public List<Zjqd> findjjsx(String fydm, String user,String lclx,int start,int number,String zt) {
		List<Zjqd> list = new ArrayList<Zjqd>();
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = " FROM Zjqd WHERE TIMESTAMPDIFF(DAY,CURDATE(),sxsj)<=CEILING(sx/3) and TIMESTAMPDIFF(DAY,CURDATE(),sxsj)>=0 and id.fydm='"+fydm+"' and sjr='"+user+"' and lclx='"+lclx+"' and zt in("+zt+")";
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = (List<Zjqd>)query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
			return list;
		}
	}
	
	public int countcgsx(String fydm, String user,String lclx,String zt) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = " FROM Zjqd WHERE TIMESTAMPDIFF(DAY,CURDATE(),sxsj)<0 and id.fydm='"+fydm+"' and sjr='"+user+"' and lclx='"+lclx+"' and zt in("+zt+")";
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return list.size();

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}
	
	public int countcgsx_fwzx(String fydm, String user,String lclx,String zt) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = " FROM Zjqd WHERE TIMESTAMPDIFF(DAY,CURDATE(),sxsj)<0 and id.fydm='"+fydm+"'  and lclx='"+lclx+"' and zt in("+zt+")";
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return list.size();

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}
	
	
	public List<Zjqd> findcgsx(String fydm, String user,String lclx,int start,int number,String zt) {
		List<Zjqd> list = new ArrayList<Zjqd>();
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = " FROM Zjqd WHERE TIMESTAMPDIFF(DAY,CURDATE(),sxsj)<0 and id.fydm='"+fydm+"' and sjr='"+user+"' and lclx='"+lclx+"' and zt in ("+zt+")";
			Query query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = (List<Zjqd>)query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
			return list;
		}
	}
	
	
	public int countByZt8(String fydm, String user, int zt) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Zjqd where id.fydm='" + fydm + "'"
					+ " and sjrbm='" + user + "' and zt=" + zt;
			List list = session.createQuery(sql).list();
			session.getTransaction().commit();
			return list.size();

		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
			return 0;
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
	}
	

}
