package cn.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.BackendUser;
import cn.app.pojo.Info;

public interface BackendUserMapper {

	public List<BackendUser> getuserCodeAnduserPwd(BackendUser backendUser);
	//根据条件查询所有信息
	public List<Info> getSelectResult(@Param("softwareName") String softwareName,@Param("flatformId") int flatformId,
			@Param("categoryLevel1") int categoryLevel1,@Param("categoryLevel2")int categoryLevel2,
			@Param("categoryLevel3")int categoryLevel3,@Param("pageIndex")int currentPageNo,@Param("pageSize")int pageSize);
	/*查询总条数*/
	public int getSelectCount(@Param("softwareName") String softwareName,@Param("flatformId") int flatformId,
			@Param("categoryLevel1") int categoryLevel1,@Param("categoryLevel2")int categoryLevel2,
			@Param("categoryLevel3")int categoryLevel3);
}
