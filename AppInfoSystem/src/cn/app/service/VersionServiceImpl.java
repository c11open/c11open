package cn.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.VersionMapper;
import cn.app.pojo.Version;

@Service("versionService")
public class VersionServiceImpl implements VersionService {

	@Autowired
	VersionMapper versionMapper;
	//版本信息
	@Override
	public List<Version> findSelectVersionInfo(int aid, int vid) {
		// TODO Auto-generated method stub
		return versionMapper.getSelectVersionInfo(aid, vid);
	}
}
