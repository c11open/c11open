package cn.app.dao;

import java.util.List;

import cn.app.pojo.Version;

public interface VersionMapper {
	
	public int addVersion(Version version);
	
	public int deleteVersion(int id);
	
	public Version getVersionByIdTwo(int id);
	
	public int UpdateVersion(Version version);
	
	public List<Version> getId();
}
