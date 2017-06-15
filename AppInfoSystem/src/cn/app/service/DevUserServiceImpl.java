package cn.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.DevUserMapper;
import cn.app.pojo.DevUser;
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
	@Autowired
	DevUserMapper devUserMapper;
	@Override
	public boolean finddevCodeAnddevPwd(DevUser devuser) {
	List<DevUser> list=devUserMapper.getdevCodeAnddevPwd(devuser);
		if(list.size()==1){
			return true;
		}else{
			return false;
		}
	}

}
