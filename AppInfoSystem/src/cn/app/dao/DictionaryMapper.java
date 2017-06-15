package cn.app.dao;


import java.util.List;

import cn.app.pojo.Dictionary;


public interface DictionaryMapper {
	
	//查询所属平台
	public List<Dictionary> getSelectPingTai();
}
