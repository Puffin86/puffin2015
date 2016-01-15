package com.ims.test;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ims.pojos.Part;
import com.ims.pojos.User;
import com.ims.services.IPartService;
import com.ims.services.IUserService;


public class SSMMain {
	public static void main(String[] args){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/application-config.xml");
//		simple1(ac);
//		simple2(ac);
//		simple3(ac);
//		simple4(ac);
//		simple5(ac);
		simple6(ac);
	}
	
	public static void simple1(ApplicationContext ac ){
		IUserService userService = (IUserService) ac.getBean("userService");  
		User user = userService.getUserById(1);
		System.out.println(user.getUserName()); 
	}
	
	public static void simple2(ApplicationContext ac ){
		IPartService partService = (IPartService) ac.getBean("partService");  
		Part part = partService.selectAllByPrimaryKey("a");
		List<User> users = part.getUsers();
		for(User u : users){
			System.out.println(u.getUserName());
		}
		System.out.println(part.getName()); 
	}
	
	public static void simple3(ApplicationContext ac ){
		IPartService partService = (IPartService) ac.getBean("partService");  
		Map<String,Object> part = partService.selectAllByPrimaryKeyMap("a");
		System.out.println(part.size()); 
	}
	
	public static void simple4(ApplicationContext ac ){
		IPartService partService = (IPartService) ac.getBean("partService");  
		int num = partService.selectNum();
		System.out.println(num); 
	}
	
	public static void simple5(ApplicationContext ac ){
		IPartService partService = (IPartService) ac.getBean("partService");  
		String sql = "create table dynamicTable ( id int not null , author_id int not null,title varchar(255),primary key (id))";
		partService.dynamicDDLSQL(sql);
	}
	
	public static void simple6(ApplicationContext ac ){
		IPartService partService = (IPartService) ac.getBean("partService");  
		partService.dropTable("dynamicTable");
	}
	
	
	
	
	
	
}
