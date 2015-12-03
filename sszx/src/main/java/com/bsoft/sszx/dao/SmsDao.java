package com.bsoft.sszx.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class SmsDao {
	Session session = null;	//Session对象
	Transaction tx = null;	//事物
	/** shaobt  版权所有 2015-08-03
	 * 本源码不对外开放、商业用途
	 * 查询所有图书
	 * @return List
	 */
	
	public void saveOrUpdate(Sms Sms){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();		
	    	session.saveOrUpdate(Sms);//应用save()方法将留言信息保存到数据
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
     } 
   } 
	
	
	public int getMaxId(){
		try{
    		session = HibernateUtil.getSession();		//获取Session
    		session.beginTransaction();
	    	Object maxId = session.createSQLQuery("select max() from sms").uniqueResult();
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
		}
		return 1;
	}

}
