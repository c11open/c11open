package cn.app.dao;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;

public interface InfoMapper {
		
	public int getCountInfo(@Param("APKName") String APKName);
	
	public int addInfo(Info info);
	
	public int UpdateInfoVersionIDById(@Param("id") int id,@Param("versionId")int versionId);
	
	public int UpdateInfoStatusById(@Param("id")int id,@Param("status")int status);
	
	public int getAppBasicInfo(Info info);
}
