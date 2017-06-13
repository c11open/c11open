package cn.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.app.pojo.DevUser;
import cn.app.service.DevUserService;

@RequestMapping("/dev")
@Controller
public class DevUserController {
	@Autowired
	DevUserService devUserService;
	//跳转到开发者登录
	@RequestMapping(value="/devlogin.html",method=RequestMethod.GET)
	public String devUserlogin(){
		return  "devlogin";
	}
	//登录验证
	@RequestMapping(value="/devdologin.html",method=RequestMethod.POST)
	public String devlogin(@RequestParam String devCode,@RequestParam String devPassword ,HttpSession session,HttpServletRequest request){
		DevUser devuser = new DevUser();
		devuser.setDevCode(devCode);
		devuser.setDevPassword(devPassword);
		boolean flag = devUserService.finddevCodeAnddevPwd(devuser);
		System.out.println(flag+"************************************");
		if(flag){
			session.setAttribute("devUserSession",devuser);
			return "/developer/main";
		}else{
			request.setAttribute("error", "用户名或密码错误！");
			return "/devlogin";
		}
	}
	
}
