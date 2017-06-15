package cn.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;

public interface InfoMapper {
	//App审核
	public List<Info> getSelectAppCheck(@Param("aid") int aid,@Param("vid") int vid);
	//App审核通过、未通过审核
	public int getUpdataStatus(@Param("status") int status,@Param("id") int id);
}
