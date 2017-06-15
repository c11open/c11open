package cn.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.BackendUserMapper;
import cn.app.pojo.BackendUser;
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {
	@Autowired
	BackendUserMapper backendUserMapper;
	@Override
	public boolean finduserCodeAnduserPwd(BackendUser backendUser) {
		List<BackendUser> list=backendUserMapper.getuserCodeAnduserPwd(backendUser);
		if(list.size()==1){
			return true;
		}else{
			return false;
		}
	}

}
