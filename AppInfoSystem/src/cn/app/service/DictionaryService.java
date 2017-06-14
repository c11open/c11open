package cn.app.service;

import java.util.List;

import cn.app.pojo.Dictionary;


public interface DictionaryService {

	//查询所属平台
	public List<Dictionary> findSelectPingTai();
}
