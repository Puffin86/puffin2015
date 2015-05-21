package com.bsoft.sszx.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.eaj.Eaj;
import com.bsoft.sszx.entity.edsr.Edsr;
import com.bsoft.sszx.hibernate.HibernateSybase;

public class ECourtDao {

	Session session = null;
	Transaction tx = null;

	public List findAh(String cxn, String AnHao,String dsr) {
		List list = null;
		try {
			session = HibernateSybase.getSession();
			Query query = session.createQuery("from Eaj where ah like '%" +  AnHao + "%' and dsr like '%"+dsr+"%' and nd='"+cxn+"'");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase.closeSession(session);
			return list;// 关闭Session
		}
	}

	public Eaj findByAh(String ah) {
		Eaj eaj = null;
		try {
			session = HibernateSybase.getSession();
			Query query = session.createQuery("from Eaj where ah =" + "'" + ah
					+ "'");
			eaj = (Eaj) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase.closeSession(session);
			return eaj;// 关闭Session
		}
	}

	public List findEdsr(String ahdm) {
		List list = null;
		try {
			session = HibernateSybase.getSession();
			Query query = session.createQuery("from Edsr where id.ahdm =" + "'"
					+ ahdm + "'");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase.closeSession(session);
			return list;// 关闭Session
		}
	}
	
	public Edsr findEdsr(String ahdm,String dsrmc) {
		Edsr edsr = null;
		try {
			session = HibernateSybase.getSession();
			Query query = session.createQuery("from Edsr where id.ahdm =" + "'"
					+ ahdm + "'");
			edsr = (Edsr)query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase.closeSession(session);
			return edsr;
		}
	}

	public List findAhByUser(String cxn, String AnHao,String dsr, String user) {
		List list = null;
		try {
			session = HibernateSybase.getSession();
			Query query = session.createQuery("from Eaj where ah " + "like '%"+ AnHao + "%'" + " and cbr='" + user + "' and dsr like '%"+dsr+"%'" +" and nd='"+cxn+"'");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase.closeSession(session);
			return list;// 关闭Session
		}
	}
	
	
	public Eaj findAyByAh(String ah) {
		Eaj eaj = null;
		try {
			session = HibernateSybase.getSession();
			Query query = session.createQuery("from Eaj where ah ='" + ah+ "'");
			eaj = (Eaj) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
		} finally {
			HibernateSybase.closeSession(session);
			return eaj;// 关闭Session
		}
	}
	
	

}
