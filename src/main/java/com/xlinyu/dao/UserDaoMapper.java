package com.xlinyu.dao;

import com.xlinyu.domain.User;

public interface UserDaoMapper {

	User findUserByUsername(String username);
	
}
