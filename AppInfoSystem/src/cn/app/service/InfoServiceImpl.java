package cn.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.InfoMapper;
import cn.app.pojo.Info;

@Service("InfoService")
public class InfoServiceImpl implements InfoService {
	@Autowired
	InfoMapper infoMapper;
	@Override
	public boolean getCountInfo(String APKName) {
		int row = infoMapper.getCountInfo(APKName);
		if(row!=0){
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public boolean addInfo(Info info) {
		int row = infoMapper.addInfo(info);
		if(row==1){
			return true;
		}else{
		return false;
		}
	}
	@Override
	public boolean UpdateInfoVersionIDById(int id, int versionId) {
		int row = infoMapper.UpdateInfoVersionIDById(id, versionId);
		if(row==1){
			return true;
		}else{
		return false;
		}
	}
	@Override
	public boolean UpdateInfoStatusById(int id, int status) {
		int row = infoMapper.UpdateInfoStatusById(id, status);
		if(row==1){
			return true;
		}else{
		return false;
		}
	}
	@Override
	public boolean findAppBasicInfo(Info info) {
		int row=infoMapper.getAppBasicInfo(info);
		if(row==1){
			return true;
		}else{
			return false;
		}
	}
}
