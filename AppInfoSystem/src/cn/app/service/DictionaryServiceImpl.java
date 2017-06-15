package cn.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.DictionaryMapper;
import cn.app.dao.InfoMapper;
import cn.app.pojo.Dictionary;
import cn.app.pojo.Info;
@Service("dictionService")
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	DictionaryMapper dictionaryMapper;
	//查询所属平台

	@Override
	public List<Dictionary> findSelectPingTai() {
		
		return dictionaryMapper.getSelectPingTai();
	}
	
	
}
