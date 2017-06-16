package cn.app.service;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;

public interface InfoService {
	
	public boolean getCountInfo(String APKName);
	
	public boolean addInfo(Info info);
	
	public boolean UpdateInfoVersionIDById(int id,int versionId);
	
	public boolean UpdateInfoStatusById(int id,int status);
	
	public boolean findAppBasicInfo(Info info);
}
