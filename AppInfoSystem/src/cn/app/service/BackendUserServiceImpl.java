package cn.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.BackendUserMapper;
import cn.app.pojo.BackendUser;
import cn.app.pojo.Info;
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {
	@Autowired
	BackendUserMapper backendUserMapper;
	@Override
	public BackendUser finduserCodeAnduserPwd(BackendUser backendUser) {
		List<BackendUser> list=backendUserMapper.getuserCodeAnduserPwd(backendUser);
		return list.get(0);
	}
	//根据条件查询信息
	@Override
	public List<Info> findSelectResult(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int pageIndex,int pageSize) {
		
		return backendUserMapper.getSelectResult(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, (pageIndex-1)*pageSize,pageSize);
	}
	//查询符合条件的记录数
	@Override
	public int findSelectCount(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3) {
		
		return backendUserMapper.getSelectCount(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}
	
	
}
