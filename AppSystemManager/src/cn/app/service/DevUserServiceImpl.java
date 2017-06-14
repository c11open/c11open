package cn.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.DevUserMapper;
import cn.app.pojo.Category;
import cn.app.pojo.DevUser;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
import cn.app.pojo.Version;
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
	@Autowired
	DevUserMapper devUserMapper;
	@Override
	public DevUser finddevCodeAnddevPwd(DevUser devuser) {
	DevUser list=devUserMapper.getdevCodeAnddevPwd(devuser);
		return list;
	}
	@Override
	public List<Info> getInfos(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return devUserMapper.getInfos(map);
	}
	@Override
	public List<Dictionary> getValueName() {
		// TODO Auto-generated method stub
		return devUserMapper.getValueName();
	}
	@Override
	public List<Dictionary> getValueNameBy(){
		// TODO Auto-generated method stub
		return devUserMapper.getValueNameBy();
	}
	@Override
	public List<Category> getCategoryName(int parentId) {
		// TODO Auto-generated method stub
		return devUserMapper.getCategoryName(parentId);
	}
	@Override
	public int getInfoCount(Info info) {
		// TODO Auto-generated method stub
		return devUserMapper.getInfoCount(info);
	}
	@Override
	public Info getInfoById(int id) {
		// TODO Auto-generated method stub
		return devUserMapper.getInfoById(id);
	}
	@Override
	public List<Version> getVersionById(int id) {
		// TODO Auto-generated method stub
		return devUserMapper.getVersionById(id);
	}
	
	
	

}
