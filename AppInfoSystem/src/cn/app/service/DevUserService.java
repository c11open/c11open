package cn.app.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Category;
import cn.app.pojo.DevUser;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
import cn.app.pojo.Version;

public interface DevUserService {

	public DevUser finddevCodeAnddevPwd(DevUser devuser);
	
	public List<Info> getInfos(Map<String,Object> map);
	
	public List<Dictionary> getValueName();
	
	public List<Dictionary> getValueNameBy();
	
	public List<Category> getCategoryName(int parentId);
	
	public int getInfoCount(Info info);
	
	public Info getInfoById(int id);
	
	public List<Version> getVersionById(int id);
}
