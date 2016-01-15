package com.ims.services.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ims.mappers.PartMapper;
import com.ims.pojos.Part;
import com.ims.services.IPartService;

@Service("partService") 
public class PartServiceImpl implements IPartService {

	@Resource
	private PartMapper partMapper;
	@Override
	public Part selectAllByPrimaryKey(String code) {
		// TODO Auto-generated method stub
		return partMapper.selectAllByPrimaryKey(code);
	}
	@Override
	public Map<String, Object> selectAllByPrimaryKeyMap(String code) {
		// TODO Auto-generated method stub
		return partMapper.selectAllByPrimaryKeyMap(code);
	}
	@Override
	public int selectNum() {
		// TODO Auto-generated method stub
		return partMapper.selectNum();
	}
	@Override
	public void dynamicDDLSQL(String sql) {
		// TODO Auto-generated method stub
		partMapper.dynamicDDLSQL(sql);
	}
	@Override
	public void dropTable(String tableName) {
		// TODO Auto-generated method stub
		partMapper.dropTable(tableName);
	}

}
