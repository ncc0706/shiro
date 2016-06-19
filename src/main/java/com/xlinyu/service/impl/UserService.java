package com.xlinyu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xlinyu.dao.UserDaoMapper;
import com.xlinyu.domain.User;
import com.xlinyu.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Resource
	private UserDaoMapper userDaoMapper;
	
	@Override
	public User findUserByUsername(String username) {
		return userDaoMapper.findUserByUsername(username);
	}

}
