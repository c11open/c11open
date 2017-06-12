package cn.app.dao;

import java.util.List;

import cn.app.pojo.DevUser;

public interface DevUserMapper {

	public List<DevUser> getdevCodeAnddevPwd(DevUser devuser);
}
