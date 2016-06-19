package com.xlinyu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/userinfo")  
    public String getUserInfo(HttpServletRequest request){  
        String currentUser = (String)request.getSession().getAttribute("currentUser");  
        System.out.println("当前登录的用户为[" + currentUser + "]");  
        request.setAttribute("currUser", currentUser);  
        return "user/userinfo";  
    }
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(){
		
		return "user/users";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(){
		return "user/list";
	}
	
}
