package cn.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.app.pojo.BackendUser;
import cn.app.pojo.Category;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
import cn.app.pojo.Page;
import cn.app.pojo.Version;
import cn.app.service.BackendUserService;
import cn.app.service.CategoryService;
import cn.app.service.DictionaryService;
import cn.app.service.InfoService;
import cn.app.service.VersionService;

@Controller
public class BackendUserController {
	
	@Autowired
	BackendUserService backendUserService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	InfoService infoService;
	@Autowired
	VersionService versionService;
	//登录
	@RequestMapping(value="/backendmanager/backendlogin.html",method=RequestMethod.GET)
	public String login(){
		return "backendlogin";
	}
	@RequestMapping(value="/backendmanager/backdologin.html",method=RequestMethod.POST)
	public String backend(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,HttpServletRequest request){
		BackendUser backend=new BackendUser();
		backend.setUserCode(userCode);
		backend.setUserPassword(userPassword);
		BackendUser backenduser=backendUserService.finduserCodeAnduserPwd(backend);
		if(backenduser.getUserName()!=null||!backenduser.getUserName().equals("")){
			session.setAttribute("userSession", backenduser);
			return "/backend/main";
		}else{
			request.setAttribute("error", "您输入的用户名或密码错误！");
			return "/backendlogin";
		}
	}
	//注销
	@RequestMapping(value="/manager/logout.html")
	public String loginout(HttpSession session){
		session.invalidate();
		return "/backendlogin";
	}
	//查询待审核App列表,处理数据
	@RequestMapping(value="/app/list.html")
	public String AppList(@RequestParam(required=false) String querySoftwareName,@RequestParam(required=false) String queryFlatformId,
			@RequestParam(required=false) String queryCategoryLevel1,@RequestParam(required=false) String queryCategoryLevel2,
			@RequestParam(required=false) String queryCategoryLevel3,@RequestParam(required=false) String pageIndex,
			@RequestParam(required=false) String pageSize,HttpServletRequest request){
		if(pageIndex==null){
			pageIndex="1";
		}
		if(queryFlatformId==null||queryFlatformId.equals("")){
			queryFlatformId="0";
		}
		if(queryCategoryLevel1==null||queryCategoryLevel1.equals("")){
			queryCategoryLevel1="0";
		}
		if(queryCategoryLevel2==null||queryCategoryLevel2.equals("")){
			queryCategoryLevel2="0";
		}
		if(queryCategoryLevel3==null||queryCategoryLevel3.equals("")){
			queryCategoryLevel3="0";
		}
		Page page=new Page();
		page.setPageSize(4);
		page.setCurrentPageNo(Integer.parseInt(pageIndex));
		//根据条件查询所有信息
		List<Info> list=backendUserService.findSelectResult(querySoftwareName, Integer.parseInt(queryFlatformId),
				Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), 
				Integer.parseInt(queryCategoryLevel3),page.getCurrentPageNo(),page.getPageSize());
		request.setAttribute("appInfoList", list);
		
		//根据条件查询总记录数
		int count=backendUserService.findSelectCount(querySoftwareName, Integer.parseInt(queryFlatformId),
				Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), 
				Integer.parseInt(queryCategoryLevel3));
		page.setTotalCount(count);//总记录数
		request.setAttribute("pages", page);
				
		//所属平台
		List<Dictionary> dictionlist=dictionaryService.findSelectPingTai();
		request.setAttribute("flatFormList", dictionlist);
		//一级查询信息
		List<Category> catelist1=categoryService.findSelectcategoryLevel1();
		request.setAttribute("categoryLevel1List", catelist1);
		
		request.setAttribute("querySoftwareName", querySoftwareName);
		
		return "/backend/applist";
	}
	
	//二级查询信息
	@RequestMapping(value="/backend/apptwolist")
	@ResponseBody
	public Object TwoList(@RequestParam String pid){
		List<Category> list=categoryService.findSelectcategoryLevel2(Integer.parseInt(pid));
		return JSONArray.toJSONString(list);
	}
	//三级查询信息
	@RequestMapping(value="/backend/appthreelist")
	@ResponseBody
	public Object Three(@RequestParam String pid){
		List<Category> list=categoryService.findSelectcategoryLevel3(Integer.parseInt(pid));
		return JSONArray.toJSONString(list);
	}
	//APP审核
	@RequestMapping(value="/app/check.html")
	public String AppShenHe(@RequestParam int aid,@RequestParam int vid,HttpServletRequest request){
		//App基础信息
		List<Info> list=infoService.findSelectAppCheck(aid, vid);
		request.setAttribute("appInfo", list.get(0));
		//App版本信息
		List<Version> versionlist=versionService.findSelectVersionInfo(aid, vid);
		request.setAttribute("appVersion", versionlist.get(0));
		return "/backend/appcheck";
	}
	@RequestMapping(value="/app/checksave.html")
	public String appCheck(@RequestParam String status,@RequestParam String id,HttpServletRequest request){
		//App审核通过、未通过审核
		int objstatus=infoService.findUpdataStatus(Integer.parseInt(status), Integer.parseInt(id));
		if(objstatus==1){
			return "redirect:/app/list.html";
		}else{
			return "/backend/appcheck";
		}
		
	}
}

