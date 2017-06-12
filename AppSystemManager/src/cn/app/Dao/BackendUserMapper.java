package cn.app.dao;

import java.util.List;

import cn.app.pojo.BackendUser;

public interface BackendUserMapper {

	public List<BackendUser> getuserCodeAnduserPwd(BackendUser backendUser);
}
