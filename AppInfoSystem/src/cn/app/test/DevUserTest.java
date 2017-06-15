package cn.app.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.app.pojo.DevUser;
import cn.app.service.BackendUserService;
import cn.app.service.DevUserService;


public class DevUserTest {
	private static Logger log=Logger.getLogger(DevUserTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("SpringConfig.xml");
		DevUserService devuser =(DevUserService)context.getBean("devUserService");
		DevUser dev=new DevUser();
		dev.setDevCode("test001");
		dev.setDevPassword("123456");
		boolean flag=devuser.finddevCodeAnddevPwd(dev);
		log.info("PL:"+flag);
	}

}
