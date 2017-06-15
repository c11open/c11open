package cn.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Category;

public interface CategoryService {
	//查询一级标题
	public List<Category> findSelectcategoryLevel1();
	//查询二级标题
	public List<Category> findSelectcategoryLevel2(int parentId);
	//查询三级标题
	public List<Category> findSelectcategoryLevel3(int parentId);
}
