package com.kongxiaogang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kongxiaogang.model.UserModel;
import com.kongxiaogang.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/showUser.do")
	public String showUser(String userId,HttpServletRequest request) {
		UserModel user = userService.getUserByID(userId);
		request.setAttribute("user", user);
		return "showUser";
	}
}
