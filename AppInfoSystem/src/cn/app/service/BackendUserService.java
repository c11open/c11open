package cn.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.app.pojo.BackendUser;
import cn.app.pojo.Info;

public interface BackendUserService {
	//登录
	public BackendUser finduserCodeAnduserPwd(BackendUser backendUser);
	//根据条件查询信息
	public List<Info> findSelectResult(String softwareName,int flatformId,int categoryLevel1,
			int categoryLevel2,int categoryLevel3,int pageIndex,int pageSize);
	//查询符合条件的记录数
	public int findSelectCount(String softwareName,int flatformId,int categoryLevel1,
			int categoryLevel2,int categoryLevel3);
}
