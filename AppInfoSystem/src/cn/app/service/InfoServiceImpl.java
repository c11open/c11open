package cn.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.InfoMapper;
import cn.app.pojo.Info;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	InfoMapper infoMapper;
	//App审核
	@Override
	public List<Info> findSelectAppCheck(int aid,int vid) {
		return infoMapper.getSelectAppCheck(aid, vid);
	}
	//App审核通过、未通过审核
	@Override
	public int findUpdataStatus(int status, int id) {
		
		return infoMapper.getUpdataStatus(status, id);
	}
	
}
