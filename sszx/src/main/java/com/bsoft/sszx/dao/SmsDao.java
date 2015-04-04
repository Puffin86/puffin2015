package com.bsoft.sszx.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.hibernate.HibernateUtil;

public class SmsDao {
	Session session = null;	//Session对象
	Transaction tx = null;	//事物
	/**
	 * 查询所有图书
	 * @return List
	 */
	
	public void save(Sms Sms){
    	try{
    		session = HibernateUtil.getSession();		//获取Session
			session.beginTransaction();		
	    	session.save(Sms);//应用save()方法将留言信息保存到数据
	    	session.getTransaction().commit();
    	} catch (Exception e) {
			e.printStackTrace();						//打印错误信息
		}finally{
			HibernateUtil.closeSession(session);		//关闭Session
     } 
   } 

}
