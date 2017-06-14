package cn.app.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

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

import cn.app.pojo.Version;
import cn.app.service.VersionService;

@RequestMapping("/dev")
@Controller
public class VersionController {
	@Autowired
	VersionService versionService;
	
	//新增版本页面跳转
		@RequestMapping(value="appversionadd/{appinfoid}")
		public String appversionadd(@PathVariable String appinfoid,HttpSession Session){
			Session.setAttribute("appinfoid",appinfoid);
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
					return "redirect:/dev/appinfolist.html";	
				}else{
					return "appversionadd";
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
	}

