package cn.app.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.app.pojo.DevUser;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
import cn.app.pojo.Version;
import cn.app.service.BackendUserService;
import cn.app.service.DevUserService;
import cn.app.service.InfoService;
import cn.app.service.VersionService;


public class DevUserTest {
	private static Logger log=Logger.getLogger(DevUserTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("SpringConfig.xml");
		InfoService devuser1 =(InfoService)context.getBean("InfoService");
		Info info = new Info();
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
		info.setVersionId(38);
		boolean flag = devuser1.addInfo(info);
		System.out.println(flag+"+++++++++++++++++++++++++++++++++");
		
	}

}
