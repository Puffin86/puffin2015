package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.bsoft.sszx.entity.user.BmBean;
import com.bsoft.sszx.entity.user.Bmb;
import com.bsoft.sszx.entity.user.User;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class UserDao {

	Session session = null; // Session对象
	Transaction tx = null; // 事物

	public Bmb findBm(String bmdm, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Bmb where id.bmdm='" + bmdm + "' and id.dwdm="
					+ fydm;
			Bmb bm = (Bmb) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();

			return bm;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public String findBmdm(String bmmc, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Bmb where bmmc='" + bmmc + "' and id.dwdm="
					+ fydm;
			Bmb bm = (Bmb) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();

			return bm.getId().getBmdm();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public User findUser(String uid, String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from User where bmdm=" + uid + " and id.dwdm=" + fydm;
			User user = (User) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	public User findUserById(String uid, String fydm) {
		User user = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from User where id.yhid='" + uid + "' and id.dwdm='"
					+ fydm + "'";
			user = (User) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();// 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return user;
	}

	public List<User> findUserAll(String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from User where dwdm=" + fydm;
			List<User> userList = (List<User>) session.createQuery(sql).list();
			session.getTransaction().commit();
			return userList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	

	public List<User> findUserByName(String fydm, String name) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from User where dwdm=" + fydm + " and yhxm like "
					+ "'%" + name + "%'";
			List<User> userList = (List<User>) session.createQuery(sql).list();
			session.getTransaction().commit();
			return userList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	
	
	@SuppressWarnings("finally")
	public User findSimpleUserByName(String fydm, String name) {
		User user = null;
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from User where dwdm=" + fydm + " and yhxm = "
					+ "'" + name + "'";
			user = (User) session.createQuery(sql).list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
			return user;
		}
		
	}

	public List<User> findUserByBm(String bm, String fydm) {
		try {
			String bmdm = findBmdm(bm, fydm);
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from User where dwdm=" + fydm + " and yhbm=" + bmdm;
			List<User> userList = (List<User>) session.createQuery(sql).list();
			session.getTransaction().commit();
			return userList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

	
	public List<User> findUserByJs(String js, String fydm,String bmdm,Boolean isjs) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT yhxm,yhid,yhbm,");
			sqlBuf.append("(SELECT bmmc FROM bmb WHERE bmdm=a.yhbm) AS bmmc,");
			sqlBuf.append("(SELECT zdmxmc FROM zdmx WHERE a.js=zdmxbm AND zdbm='js') AS jsmc,js");
			sqlBuf.append(" FROM USER a");
			sqlBuf.append(" where dwdm=").append(fydm);
			if(isjs){
				sqlBuf.append(" and js=").append(js);
			}else{
				sqlBuf.append(" and js !=").append(js);
			}
			
			if(bmdm!=null&&!"".equals(bmdm)){
				sqlBuf.append(" and yhbm like '%").append(bmdm).append("%'");
			}
			sqlBuf.append(" order by yhbm");
			SQLQuery query = session.createSQLQuery(sqlBuf.toString());
			query.addScalar("yhxm", Hibernate.STRING);
			query.addScalar("yhid", Hibernate.STRING);
			query.addScalar("yhbm", Hibernate.STRING);
			query.addScalar("bmmc", Hibernate.STRING);
			query.addScalar("jsmc", Hibernate.STRING);
			query.addScalar("js", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(User.class));
			List<User> userList = (List<User>)query.list();
			session.getTransaction().commit();
			return userList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	
	public List<Bmb> findBmAll(String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Bmb where dwdm=" + fydm;
			List<Bmb> BmbList = (List<Bmb>) session.createQuery(sql).list();
			session.getTransaction().commit();
			return BmbList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	
	public List<Bmb> findBmByBmdm(String fydm,String bmdm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "from Bmb where dwdm=" + fydm +" and bmdm like '%"+bmdm+"%'";
			List<Bmb> BmbList = (List<Bmb>) session.createQuery(sql).list();
			session.getTransaction().commit();
			return BmbList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	
	public List<BmBean> findBm(String fydm) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			String sql = "select dwdm,bmdm,bmmc from bmb where dwdm='" + fydm+"'";
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("dwdm",Hibernate.STRING);
			query.addScalar("bmdm",Hibernate.STRING);
			query.addScalar("bmmc",Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(BmBean.class));
			List<BmBean> BmbList = (List<BmBean>) query.list();
			session.getTransaction().commit();
			return BmbList;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}
	

	public String saveOrUpdateUser(User user) {
		try {
			session = HibernateUtil.getSession(); // 获取Session
			session.beginTransaction();
			session.merge(user);// 应用save()方法将留言信息保存到数据
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateUtil.closeSession(session); // 关闭Session
		}
		return null;
	}

}
