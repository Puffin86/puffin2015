package com.bsoft.sszx.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bsoft.sszx.entity.clb.Clb;
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
	
	
	public Dxx findByZt(String zt,String fydm,String bh){
		Dxx dxx = null;
		dxx = findByZt(zt,fydm);
		String nr = dxx.getNr();
		ClbDao clbdao = new ClbDao();
		List<Clb> clList = clbdao.findByZjqd(bh,fydm);
		String clnr = "材料清单：";
		if(clList.size()>0){
			for(Clb cl : clList){
				String clmc = cl.getClmc();
				clnr = clnr + clmc+",";
			}
		}
		nr = nr + clnr.substring(0, clnr.length()-1);
		dxx.setNr(nr);
		return dxx;
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
