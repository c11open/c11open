package cn.app.dao;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;

public interface InfoMapper {
		
	public int getCountInfo(@Param("APKName") String APKName);
	
	public int addInfo(Info info);
}
