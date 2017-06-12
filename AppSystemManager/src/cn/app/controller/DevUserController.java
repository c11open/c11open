package cn.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/dev")
@Controller
public class DevUserController {
	
	//跳转到开发者登录
	@RequestMapping(value="/devlogin.html")
	public String devUserlogin(){
		return  "devlogin";
	}
	//登录
	@RequestMapping(value="devlogin1.html")
	public String devlogin1(){
		
		return "";
	}
}
