package cn.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.VersionMapper;
import cn.app.pojo.Version;
@Service("VersionService")
public class VersionServiceImpl implements VersionService {
	@Autowired
	VersionMapper versionMapper;
	@Override
	public boolean addVersion(Version version) {
		int row = versionMapper.addVersion(version);
		if(row==1){
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public int aa() {
		// TODO Auto-generated method stub
		return versionMapper.aa();
	}
	@Override
	public int deleteVersion(int id) {
		// TODO Auto-generated method stub
		return versionMapper.deleteVersion(id);
	}

}
