package cn.app.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Info;


public interface InfoService {

	//App审核
	public List<Info> findSelectAppCheck(int aid,int vid);
	//App审核通过、未通过审核
	public int findUpdataStatus(int status,int id);
}
