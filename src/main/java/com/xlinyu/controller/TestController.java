package com.xlinyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/abc")
	public String abc(){
		
		return "test/abc";
	}
	
}
