package cn.app.service;

import java.util.List;

import cn.app.pojo.Version;

public interface VersionService {
	//版本信息
	public List<Version> findSelectVersionInfo(int aid,int vid);
}
