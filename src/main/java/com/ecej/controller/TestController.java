package com.ecej.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("testAction")
	public String testAction(HttpServletRequest request,HttpServletResponse response){
		String content = request.getParameter("content");
		
		System.out.println(content);
		
		return "test";
		
	}
}
