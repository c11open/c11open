package cn.app.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBinding;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.app.pojo.Category;
import cn.app.pojo.DevUser;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
import cn.app.pojo.Version;
import cn.app.service.DevUserService;
import cn.app.service.InfoService;



@RequestMapping("/dev")
@Controller
public class DevUserController {
	@Autowired
	DevUserService devUserService;
	@Autowired
	InfoService infoService;
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
		DevUser devUder = devUserService.finddevCodeAnddevPwd(devuser);
		if(devUder!=null){
			session.setAttribute("devUserSession",devUder);
			return "/developer/main";
		}else{
			request.setAttribute("error", "用户名或密码错误！");
			return "/devlogin";
		}
	}
	@RequestMapping(value="/appinfolist.html")
	public String appinfolist(HttpServletRequest request,
			@RequestParam(required=false)String pageIndex,
			@RequestParam(required=false)String querySoftwareName,
			@RequestParam(required=false)String queryStatus,
			@RequestParam(required=false)String queryFlatformId,
			@RequestParam(required=false)String queryCategoryLevel1,
			@RequestParam(required=false)String queryCategoryLevel2,
			@RequestParam(required=false)String queryCategoryLevel3){
		if(pageIndex==null){
			pageIndex="1";
		}
		if(queryStatus ==null || queryStatus.equals("")){
			queryStatus="0";
		}
		if(queryFlatformId ==null || queryFlatformId.equals("")){
			queryFlatformId="0";
		}
		if(queryCategoryLevel1 ==null || queryCategoryLevel1.equals("")){
			queryCategoryLevel1="0";
		}
		if(queryCategoryLevel2 ==null || queryCategoryLevel2.equals("")){
			queryCategoryLevel2="0";
		}
		if(queryCategoryLevel3 ==null || queryCategoryLevel3.equals("")){
			queryCategoryLevel3="0";
		}
		//查询列表
		HashMap<String, Object> map = new HashMap<String,Object>();
		Info info = new Info();
		info.setSoftwareName(querySoftwareName);
		info.setStatus(Integer.parseInt(queryStatus));
		info.setFlatformId(Integer.parseInt(queryFlatformId));
		info.setCategoryLevel1(Integer.parseInt(queryCategoryLevel1));
		info.setCategoryLevel2(Integer.parseInt(queryCategoryLevel2));
		info.setCategoryLevel3(Integer.parseInt(queryCategoryLevel3));
		map.put("Info", info);		
		int row = (Integer.parseInt(pageIndex)-1)*5;
		map.put("pageindex", row);
		map.put("pagesize", 5);
		List<Dictionary> Dictionarys = devUserService.getValueName();
		List<Dictionary> Dictionaryss =  devUserService.getValueNameBy();
		List<Category> cates = devUserService.getCategoryName(0);
		request.setAttribute("statusList", Dictionarys);
		request.setAttribute("flatFormList", Dictionaryss);	
		request.setAttribute("categoryLevel1List", cates);
		List<Info> infos = devUserService.getInfos(map);
		request.setAttribute("appInfoList", infos);
		//分页
		int count = devUserService.getInfoCount(info);
		/*int count = 15;*/
		int totalPageCount =count % 5 ==0?count/5:count/5+1;
		request.setAttribute("totalCount", count);
		request.setAttribute("currentPageNo", pageIndex);
		request.setAttribute("totalPageCount", totalPageCount);
		return "/developer/appinfolist";
	}
	//二三级
	@RequestMapping(value="categorylevellist",produces={"application/json;charset=utf-8"})
	@ResponseBody							
	public Object categorylevellist(String pid){
		List<Category> Categorys = devUserService.getCategoryName(Integer.parseInt(pid));
		return JSONArray.toJSONString(Categorys);
	}
	//查看
	@RequestMapping(value="/appview/{appinfoid}",produces={"application/json;charset=utf-8"})
	public String appview(@PathVariable String appinfoid,HttpServletRequest request){
		if(appinfoid.trim().length()==0){
			return "redirect:/dev/appinfolist.html";
		}
		Info info = devUserService.getInfoById(Integer.parseInt(appinfoid));
		request.setAttribute("appInfo", info);
		List<Version> versions = devUserService.getVersionById(Integer.parseInt(appinfoid));
		request.setAttribute("appVersionList",versions);
		return "/developer/appinfoview";
	}
	//返回
	@RequestMapping(value="/list")
	public String returnA(){
		return "redirect:/dev/appinfolist.html";
	}
	//注销
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/dev/devlogin.html";
	}
	//新增APP基础信息页面跳转
	@RequestMapping(value="/appinfoadd.html")
	public String appinfoadd(){
		return "/developer/appinfoadd";
	}
	//添加一级
	@RequestMapping(value="/categorylevellist1",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object categorylevellist1(String pid){
		if(pid.trim().length()==0){
			pid="0";
		}
		List<Category> Categorys = devUserService.getCategoryName(Integer.parseInt(pid));
		return JSONArray.toJSONString(Categorys);
	}
	//appInfo 所属平台
	@RequestMapping(value="/datadictionarylist",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object datadictionarylist(String tcode){
		List<Dictionary> dictionarys=devUserService.getValueNameBy();
		return JSONArray.toJSONString(dictionarys);
	}
	//addInfo APKName 验证
	@RequestMapping(value="apkexist.html")
	@ResponseBody
	public Object apkexist(String APKName){
		HashMap<String, String> request = new HashMap<String, String>();
		if(APKName.trim().length()==0){
			request.put("APKName", "empty");
		}else{
			boolean flas = infoService.getCountInfo(APKName);
			if(flas){
				request.put("APKName", "exist");
			}else{
				request.put("APKName", "noexist");
			}
		}	
		return JSONArray.toJSONString(request);
	}
	//addInfo
	@RequestMapping(value="/appinfoaddsave.html",method=RequestMethod.POST)
	public String appinfoaddsave(String softwareName,String APKName,String supportROM,
						String interfaceLanguage,String softwareSize,String downloads,
						String flatformId,String categoryLevel1,String categoryLevel2,
						String categoryLevel3,String appInfo,
							HttpSession session,HttpServletRequest request,
			 @RequestParam(value ="a_logoPicPath", required = false) MultipartFile attach){
		Info info = new Info();
		info.setSoftwareName(softwareName);
		info.setAPKName(APKName);
		info.setSupportROM(supportROM);
		info.setInterfaceLanguage(interfaceLanguage);
		info.setSoftwareSize(Double.valueOf(softwareSize));
		info.setDownloads(Integer.parseInt(downloads));
		info.setFlatformId(Integer.parseInt(flatformId));
		info.setCategoryLevel1(Integer.parseInt(categoryLevel1));
		info.setCategoryLevel2(Integer.parseInt(categoryLevel2));
		info.setCategoryLevel3(Integer.parseInt(categoryLevel3));
		info.setAppInfo(appInfo);
		String idPicPath = null;
		//判断文件是否为空
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");			
			String oldFileName = attach.getOriginalFilename();//原文件名		
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀    	       
			int filesize = 500000000;
			
	        if(attach.getSize() >  filesize){//上传大小不得超过 500k
            	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
            	return "redirect:/dev/appinfolist.html";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	attach.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    request.setAttribute("uploadFileError", " * 上传失败！");
                    return "redirect:/dev/appinfolist.html";
                }  
                idPicPath = path+File.separator+fileName;
            }else{
            	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
            	return "redirect:/dev/appinfolist.html";
            }
		}
		info.setDevId(1);
		info.setStatus(1);
		info.setUpdateDate(null);
		info.setOnSaleDate(null);
		info.setOffSaleDate(null);
		info.setCreatedBy(1);
		info.setCreationDate(new Date());
		info.setModifyBy(0);
		info.setModifyDate(null);
		info.setLogoLocPath("最近去电脑看");
		info.setLogoPicPath(idPicPath);
		info.setVersionId(38);
		boolean flag = infoService.addInfo(info);
		if(flag){
			return "redirect:/dev/appinfolist.html";
		}else{
			return "/developer/appinfoadd";
		}
	}
	}

