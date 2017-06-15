package cn.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.Category;

public interface CategoryMapper {
	//查询一级标题
	public List<Category> getSelectcategoryLevel1();
	//查询二级标题
	public List<Category> getSelectcategoryLevel2(@Param("parentId") int parentId);
	//查询三级标题
	public List<Category> getSelectcategoryLevel3(@Param("parentId") int parentId);
}
