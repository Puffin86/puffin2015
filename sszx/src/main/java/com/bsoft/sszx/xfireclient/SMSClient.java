package com.bsoft.sszx.xfireclient;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

public class SMSClient {
	
	public boolean sendSMS(){
		boolean serviceFlag = false;
		try {
			Client client = new Client(new URL("http://localhost:8080/xfire/services/MathService?wsdl")); 
			Object[] result1 = client.invoke("add", new Object[] {1, 2}); 
			System.out.println(result1[0]); 
			serviceFlag = true;
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}finally{
			return true;
		}
		
	}
	
	public void searchSMS(){
		
		
	}
	
	public boolean delSMS(){
		boolean serviceFlag = false;
		try {
			Client client = new Client(new URL("http://localhost:8080/xfire/services/MathService?wsdl")); 
			Object[] result1 = client.invoke("add", new Object[] {1, 2}); 
			System.out.println(result1[0]); 
			serviceFlag = true;
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}finally{
			return true;
		}
	}
	
}
