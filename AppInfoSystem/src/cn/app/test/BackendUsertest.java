package cn.app.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.app.pojo.BackendUser;
import cn.app.service.BackendUserService;


public class BackendUsertest {
	private static Logger log=Logger.getLogger(BackendUser.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("SpringConfig.xml");
		BackendUserService backend =(BackendUserService)context.getBean("backendUserService");
		BackendUser backuser=new BackendUser();
		backuser.setUserCode("admin");
		backuser.setUserPassword("123456");
		boolean flag=backend.finduserCodeAnduserPwd(backuser);
		log.info("PL:"+flag);
	}

}
