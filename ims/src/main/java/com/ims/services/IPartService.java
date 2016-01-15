package com.ims.services;

import java.util.Map;

import com.ims.pojos.Part;

public interface IPartService {
	public Part selectAllByPrimaryKey(String code);
	
	public Map<String,Object> selectAllByPrimaryKeyMap(String code);
	
	public int selectNum();
	
	public void dynamicDDLSQL(String sql);
	
	public void dropTable(String tableName);
	
}
