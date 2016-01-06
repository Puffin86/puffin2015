package com.bsoft.sszx.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.bsoft.sszx.entity.eaj.Eaj;
import com.bsoft.sszx.entity.eaj.Eaj4ZX;
import com.bsoft.sszx.entity.edsr.Edsr;
import com.bsoft.sszx.hibernate.HibernateSybase4ZX;

public class ECourt4ZXDao {

	Session session = null;
	Transaction tx = null;

	@SuppressWarnings("finally")
	public List findAh(String cxn, String AnHao,String dsr) {
		List list = null;
		try {
			session = HibernateSybase4ZX.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select distinct new Eaj4ZX(ah ,ajbs,cbr) from Eaj4ZX where ah like '%" +  AnHao + "%' and mc like '%"+dsr+"%' and ajnh like '%"+cxn+"%'");
//			query.setResultTransformer(Transformers.aliasToBean(Eaj4ZX.class));
			list = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase4ZX.closeSession(session);
			return list;// 关闭Session
		}
	}

	public Eaj findByAh(String ah) {
		Eaj eaj = null;
		try {
			session = HibernateSybase4ZX.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Eaj4ZX where ah =" + "'" + ah
					+ "'");
			eaj = (Eaj) query.list().get(0);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase4ZX.closeSession(session);
			return eaj;// 关闭Session
		}
	}

	@SuppressWarnings("finally")
	public List findEdsr(String ahdm) {
		List list = null;
		try {
			session = HibernateSybase4ZX.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Eaj4ZX where ajbs =" + "'"+ ahdm + "'");
			list = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase4ZX.closeSession(session);
			return list;// 关闭Session
		}
	}
	
	@SuppressWarnings("finally")
	public Eaj4ZX findEdsr(String ahdm,String dsrmc) {
		Eaj4ZX eaj = null;
		try {
			session = HibernateSybase4ZX.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Eaj4ZX where ajbs =" + "'"
					+ ahdm + "' and mc='"+dsrmc+"'");
			eaj = (Eaj4ZX)query.list().get(0);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase4ZX.closeSession(session);
			return eaj;
		}
	}

	@SuppressWarnings({ "finally", "rawtypes" })
	public List findAhByUser(String cxn, String AnHao,String dsr, String user) {
		List list = null;
		try {
			session = HibernateSybase4ZX.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select distinct new Eaj4ZX(ah ,ajbs,cbr) from Eaj4ZX where ah " + "like '%"+ AnHao + "%'" 
					+ " and cbr like '%" + user + "%' and mc like '%"
					+dsr+"%'" +" and ajnh like '%"+cxn+"%'");
			list = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase4ZX.closeSession(session);
			return list;// 关闭Session
		}
	}
	
	
	@SuppressWarnings("finally")
	public Eaj findAyByAh(String ah) {
		Eaj eaj = null;
		try {
			session = HibernateSybase4ZX.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Eaj4ZX where ah ='" + ah+ "'");
			eaj = (Eaj) query.list().get(0);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase4ZX.closeSession(session);
			return eaj;// 关闭Session
		}
	}
	
	

}
