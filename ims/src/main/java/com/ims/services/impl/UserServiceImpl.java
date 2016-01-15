package com.ims.services.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ims.mappers.UserMapper;
import com.ims.pojos.User;
import com.ims.services.IUserService;

@Service("userService") 
public class UserServiceImpl implements IUserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class); 
	@Resource  
    private UserMapper userMapper;
	@Override
	public User getUserById(int userId) {
		log.trace("======trace");  
        log.debug("======debug");  
        log.info("======info");  
        log.warn("======warn");  
        log.error("======error");  
		return userMapper.selectByPrimaryKey(userId);
	}

}
