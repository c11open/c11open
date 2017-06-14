package cn.app.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.app.pojo.BackendUser;
import cn.app.pojo.Category;
import cn.app.pojo.Info;
import cn.app.pojo.Version;
import cn.app.service.BackendUserService;
import cn.app.service.CategoryService;
import cn.app.service.InfoService;
import cn.app.service.VersionService;


public class test {
	private static Logger log=Logger.getLogger(BackendUser.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("SpringConfig.xml");
		BackendUserService backend =(BackendUserService)context.getBean("backendUserService");
		CategoryService category =(CategoryService)context.getBean("categoryService");
		InfoService infoser =(InfoService)context.getBean("infoService");
		VersionService versionSer =(VersionService)context.getBean("versionService");
		/*BackendUser backuser=new BackendUser();
		backuser.setUserCode("admin");
		backuser.setUserPassword("123456");
		BackendUser flag=backend.finduserCodeAnduserPwd(backuser);
		log.info("PL:"+flag.getUserName());*/
		/*List<Info> list=backend.findSelectResult(null,0, 0, 0, 0,0,2);
		log.info("PL---:"+list.size());
		int row=backend.findSelectCount("谷歌", 3, 0, 0, 0);
		log.info("PL***"+row);
		List<Category> catelist1=category.findSelectcategoryLevel1();
		log.info("PL:"+catelist1.size());
		List<Category> catelist2=category.findSelectcategoryLevel2(2);
		log.info("PL:"+catelist2.size());
		List<Category> catelist3=category.findSelectcategoryLevel3(3);
		log.info("PL:"+catelist3.size());*/		
		
		List<Info> infolist=infoser.findSelectAppCheck(51,37);
		log.info("PL:"+infolist.size());
		List<Version> versionlist=versionSer.findSelectVersionInfo(51, 37);
		log.info("P*****L:"+versionlist.size());
	}

}
