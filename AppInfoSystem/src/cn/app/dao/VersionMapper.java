package cn.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;
import cn.app.pojo.Version;

public interface VersionMapper {
	//版本信息
	public List<Version> getSelectVersionInfo(@Param("aid") int aid,@Param("vid") int vid);
}
