package cn.app.service;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;

public interface InfoService {
	
	public boolean getCountInfo(String APKName);
	
	public boolean addInfo(Info info);
}
