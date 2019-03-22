package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList(int user_no) {
		
		List<CategoryVo> list = sqlSession.selectList("category.getlist", user_no);
		return list;
	}

	public List<CategoryVo> getCategory(int user_no) {
		List<CategoryVo> list = sqlSession.selectList("category.getcategorylist",user_no);
		return list;
	}

	public void ajaxDelete(int no) {
		sqlSession.delete("category.delete",no);
	}

	public CategoryVo ajaxInsert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert",categoryVo);
		int no = sqlSession.selectOne("category.last_insert_id");
		
		CategoryVo vo = sqlSession.selectOne("category.insertAfterSelect",no);	
		
		return vo;
	}

	public Integer getCategoryNo(int user_no) {
		Integer result = sqlSession.selectOne("category.getCategoryFirstNo",user_no);
		return result;
	}
}
