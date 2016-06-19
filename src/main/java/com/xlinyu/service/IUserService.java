package com.xlinyu.service;

import com.xlinyu.domain.User;

public interface IUserService {
	
	User findUserByUsername(String username);
	
}
