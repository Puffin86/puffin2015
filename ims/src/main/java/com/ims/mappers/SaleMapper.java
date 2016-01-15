package com.ims.mappers;

import com.ims.pojos.Sale;

public interface SaleMapper {
    int deleteByPrimaryKey(String code);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);
}