package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.sms.Dxx;
import com.bsoft.sszx.entity.zd.Zd;
import com.bsoft.sszx.entity.zd.ZdMx;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class ZdDao {
	Session session = null;	//Session对象
	Transaction tx = null;	//事物
	/**
	 * 查询所有图书
	 * @return List
	 */
	public List<Zd> findAll(){
		List list = null;
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();
			String sql="from Zd "; 
			list= (List<Dxx>)session.createQuery(sql)
					.list();
	    	session.getTransaction().commit();
	    	
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);
			return list;//关闭Session
        } 
    }    
	
	
	
	
	public List<ZdMx> findZdMx(String zdbm){
		List list = null;
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();
			String sql="from ZdMx where zdbm='"+zdbm+"'"; 
			list= (List<ZdMx>)session.createQuery(sql).list();
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
			return list;
        } 
    } 
	
	public Dxx findByZt(String zt,String fydm){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();
			String sql="from Dxx where zt='"+zt+"' and id.fydm='"+fydm+"'"; 
			Dxx bm= (Dxx)session.createQuery(sql)
					.list().get(0);
	    	session.getTransaction().commit();
	    	
	    	return bm;
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
        } 
        return null;
    } 
	
	public void saveOrUpdateZd(Zd zd){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();		
	    	session.merge(zd);//应用save()方法将留言信息保存到数据
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
     } 
   } 
	
	
	public void delZd(Zd zd){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();		
	    	session.delete(zd);
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
     } 
   } 

}
