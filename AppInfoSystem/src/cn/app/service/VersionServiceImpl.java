package cn.app.service;

import java.util.List;

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
	public int deleteVersion(int id) {
		// TODO Auto-generated method stub
		return versionMapper.deleteVersion(id);
	}
	@Override
	public Version getVersionByIdTwo(int id) {
		// TODO Auto-generated method stub
		return versionMapper.getVersionByIdTwo(id);
	}
	@Override
	public boolean UpdateVersion(Version version) {
		// TODO Auto-generated method stub
		int row = versionMapper.UpdateVersion(version);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<Version> getId() {
		// TODO Auto-generated method stub
		return versionMapper.getId();
	}

}
