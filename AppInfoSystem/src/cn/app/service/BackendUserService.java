package cn.app.service;

import org.springframework.stereotype.Service;

import cn.app.pojo.BackendUser;

public interface BackendUserService {

	public boolean finduserCodeAnduserPwd(BackendUser backendUser);
}
