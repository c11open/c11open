package cn.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Version;

public interface VersionService {
	public boolean addVersion(Version version);	
	
	public int deleteVersion(int id);
	
	public Version getVersionByIdTwo(int id);
	
	public boolean UpdateVersion(Version version);
	
	public List<Version> getId();
}
