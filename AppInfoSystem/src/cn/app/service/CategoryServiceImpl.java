package cn.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.app.dao.CategoryMapper;
import cn.app.pojo.Category;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	//查询一级标题
	@Override
	public List<Category> findSelectcategoryLevel1() {
		
		return categoryMapper.getSelectcategoryLevel1();
	}
	@Override
	public List<Category> findSelectcategoryLevel2(int parentId) {
		
		return categoryMapper.getSelectcategoryLevel2(parentId);
	}
	@Override
	public List<Category> findSelectcategoryLevel3(int parentId) {
		
		return categoryMapper.getSelectcategoryLevel3(parentId);
	}

}
