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

}
