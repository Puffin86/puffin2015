package com.ims.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ims.pojos.Part;

public interface PartMapper {
    int deleteByPrimaryKey(String code);

    int insert(Part record);

    int insertSelective(Part record);

    Part selectByPrimaryKey(String code);
    
    Part selectAllByPrimaryKey(String code);
    
    Map<String,Object> selectAllByPrimaryKeyMap(String code);
    
    int selectNum();
    
    void dynamicDDLSQL(@Param("sql") String sql);
    
    void dropTable(@Param("tableName") String tableName);

    int updateByPrimaryKeySelective(Part record);

    int updateByPrimaryKey(Part record);
}