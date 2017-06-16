package cn.app.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.app.pojo.Category;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
import cn.app.pojo.Version;
import cn.app.service.DevUserService;
import cn.app.service.InfoService;
import cn.app.service.VersionService;

@RequestMapping("/dev")
@Controller
public class VersionController {
	@Autowired
	VersionService versionService;
	@Autowired
	DevUserService devUserService;
	@Autowired
	InfoService infoService;
	//新增版本页面跳转
		@RequestMapping(value="appversionadd/{appinfoid}")
		public String appversionadd(@PathVariable String appinfoid,HttpSession Session,HttpServletRequest request){
			Session.setAttribute("appinfoid",appinfoid);
			List<Version> versions = devUserService.getVersionById(Integer.parseInt(appinfoid));
			request.setAttribute("appVersionList",versions);
			return "/developer/appversionadd";
		}
	//添加版本
	@RequestMapping(value="/addversionsave.html")
	public String appversionadd1(
			HttpServletRequest request,
			HttpSession Session,
			@RequestParam(required=false) String versionNo,
			@RequestParam(required=false) String versionSize,
			@RequestParam(required=false) String publishStatus,
			@RequestParam(required=false) String versionInfo,
			@RequestParam(value="a_downloadLink", required = false)MultipartFile attach){
		if(versionSize==null){
			versionSize="0";
		}
		String idPicPath = null;  	       
		//判断文件是否为空
				if(!attach.isEmpty()){
					String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");			
					String oldFileName = attach.getOriginalFilename();//原文件名		
					String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀    	       
					int filesize = 500000;
					
			        if(attach.getSize() >  filesize){//上传大小不得超过 500k
		            	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
			        	return "appversionadd";
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
		                    return "appversionadd";
		                }  
		                idPicPath = path+File.separator+fileName;
		            }else{
		            	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
		            	return "appversionadd";
		            }
				}
				String appinfoid1 = (String) Session.getAttribute("appinfoid");
				Version version = new Version();
				int id = Integer.parseInt(appinfoid1);
				version.setAppId(id);
				version.setVersionNo(versionNo);
				version.setVersionInfo(versionInfo);
				version.setPublishStatus(3);
				version.setDownloadLink("/AppInfoSystem/statics/uploadfiles/com.google.android.inputmethod.pinyin-V1.1.1.apk");
				version.setVersionSize(Double.valueOf(versionSize));
				version.setCreatedBy(1);
				version.setCreationDate(new Date());
				version.setModifyBy(1);
				version.setModifyDate(new Date());
				version.setApkLocPath(idPicPath);
				version.setApkFileName("bbb");
				boolean flag =versionService.addVersion(version);
				if(flag){
					List<Version> vers = versionService.getId();
					boolean a = infoService.UpdateInfoVersionIDById(id,vers.get(vers.size()-1).getId());
					if(a){
						return "redirect:/dev/appinfolist.html";
					}else{
						return "/developer/appversionadd";
					}		
				}else{
					return "/developer/appversionadd";
				}
	}
	//删除
	@RequestMapping(value="/delapp.html")
	@ResponseBody
	public Object delapp(String id){
		HashMap<String, String> delResult = new HashMap<String, String>();
		int row = versionService.deleteVersion(Integer.parseInt(id));
		if(id!=null){
			delResult.put("delResult","notexist");	
		}
		if(row==1){
				delResult.put("delResult","true");	
			}else{
				delResult.put("delResult","false");
			}
				
		return JSONArray.toJSONString(delResult);
	}
	//修改版本页面跳转
	@RequestMapping(value="/appversionmodify/{versionid}/{appinfoid}")
	public String appversionmodify(@PathVariable String versionid,@PathVariable String appinfoid,HttpServletRequest request,HttpSession session){
		List<Version> versions = devUserService.getVersionById(Integer.parseInt(appinfoid));
		request.setAttribute("appVersionList",versions);
		session.setAttribute("versionid", versionid);
		Version version = versionService.getVersionByIdTwo(Integer.parseInt(versionid));
		request.setAttribute("appVersion",version);
		session.setAttribute("appinfoid", appinfoid);
		return "/developer/appversionmodify";
	}
	//修改版本
	@RequestMapping(value="/appversionmodifysave")
	public String appversionmodifysave(HttpServletRequest request,HttpSession session){
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);
		String appId1 = request.getParameter("appId");
		int appId = Integer.parseInt(appId1);
		String versionNo = request.getParameter("versionNo");
		String versionSize1 = request.getParameter("versionSize");
		double versionSize = Double.valueOf(versionSize1);
		String versionInfo = request.getParameter("versionInfo");
		String downloadLink = request.getParameter("downloadLink");
		String apkLocPath = request.getParameter("apkLocPath");
		String apkFileName = request.getParameter("apkFileName");
		Version version = new Version();
		version.setId(id);
		version.setAppId(appId);
		version.setVersionNo(versionNo);
		version.setVersionSize(versionSize);
		version.setVersionInfo(versionInfo);
		version.setDownloadLink(downloadLink);
		version.setApkLocPath(apkLocPath);
		version.setApkFileName(apkFileName);
		version.setModifyDate(new Date());
		boolean flag = versionService.UpdateVersion(version);
		if(flag){
			return "redirect:/dev/appinfolist.html";
		}else{
			int versionid = (int) session.getAttribute("versionid");
			int appinfoid = (int) session.getAttribute("appinfoid");
			return "redirect:/dev/appversionmodify/{"+versionid+"}/{"+appinfoid+"}";
		}
	}
	//上下架
	@RequestMapping("{appId}/{saleSwitch}/sale")
	@ResponseBody
	public Object sale(@PathVariable String appId,@PathVariable String saleSwitch){
		HashMap<String, String> request = new HashMap<String, String>();
		if(saleSwitch.equals("open")){//上架
			boolean flag = infoService.UpdateInfoStatusById(Integer.parseInt(appId), 4);	
			if(flag){
				request.put("errorCode", "0");
				request.put("resultMsg", "success");
			}else{
				request.put("resultMsg", "failed");
			}	
		}else if(saleSwitch.equals("close")){//下架
			boolean flag = infoService.UpdateInfoStatusById(Integer.parseInt(appId), 5);	
			if(flag){
				request.put("errorCode", "0");
				request.put("resultMsg", "success");
			}else{
				request.put("resultMsg", "failed");
			}
		}
		
		return JSONArray.toJSONString(request);
	}
	//修改APPInfo的基本信息跳转页面
	@RequestMapping(value="appinfomodify/{appinfoid}")
	public String appinfomodify(@PathVariable String appinfoid,HttpServletRequest request,HttpSession Session){
				Info info=devUserService.getInfoById(Integer.parseInt(appinfoid));
				Session.setAttribute("appinfoid", Integer.parseInt(appinfoid));
				request.setAttribute("appInfo", info);
				return "/developer/appinfomodify";
			}
	//显示所属平台
	@RequestMapping(value="/datadictionarylistA")
	@ResponseBody
	public Object appFalt(){
				List<Dictionary> list=devUserService.getValueNameBy();
				return JSONArray.toJSONString(list);
			}
	// 1、2、3
	@RequestMapping(value="/categorylevellistA")
	@ResponseBody
	public Object appCate1(String pid){
		List<Category> list=devUserService.getCategoryName(Integer.parseInt(pid));
		return JSONArray.toJSONString(list);
	}
	//UpdateInfo
	@RequestMapping(value="/appinfomodifysave.html")	
	public String appBasicXG(@RequestParam(required=false)String id,@RequestParam(required=false) 
	String softwareName,
				@RequestParam(required=false)String APKName,@RequestParam(required=false)
	String supportROM,
				@RequestParam(required=false)String interfaceLanguage,@RequestParam
	(required=false)String softwareSize,
				@RequestParam(required=false)String downloads,@RequestParam(required=false)	
	String flatformId,
				@RequestParam(required=false)String categoryLevel1,@RequestParam	
	(required=false)String categoryLevel2,
				@RequestParam(required=false)String categoryLevel3,@RequestParam	
	(required=false)String appInfo,
			HttpServletRequest request,
			HttpSession Session){
		Info info = new Info();
		info.setId(Integer.parseInt(id));
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
		
		boolean flag=infoService.findAppBasicInfo(info);
		if(flag){
			return "redirect:/dev/appinfolist.html";		
		}else{
			int appinfoid = (int) Session.getAttribute("appinfoid");
			return "redirect:/dev/appinfomodify?id="+appinfoid;
		}
	}
	}

