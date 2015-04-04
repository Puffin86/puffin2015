package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.sms.Dxx;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class DxxDao {
	Session session = null;	//Session对象
	Transaction tx = null;	//事物
	/**
	 * 查询所有图书
	 * @return List
	 */
	public List<Dxx> find(String fydm){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();
			String sql="from Dxx where id.fydm='"+fydm+"'"; 
			List list= (List<Dxx>)session.createQuery(sql)
					.list();
	    	session.getTransaction().commit();
	    	
	    	return list;
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
        } 
        return null;
    }    
	
	public Dxx findDxx(String id,String fydm){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();
			String sql="from Dxx where id.id='"+id+"' and id.fydm='"+fydm+"'"; 
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
	
	public String saveOrUpdateDxx(Dxx Dxx){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();		
	    	session.merge(Dxx);//应用save()方法将留言信息保存到数据
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
     } 
   return null;
   } 

}
