package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BlogVo> getListTitle(Map<String, Object> map) {
		
		List<BlogVo> list = sqlSession.selectList("blog.getlist-title", map);
		return list;
	}

	public int getCount(String search) {
		return sqlSession.selectOne("blog.getCount",search);
	}

	public List<BlogVo> getListId(Map<String, Object> map) {
		List<BlogVo> list = sqlSession.selectList("blog.getlist-id", map);
		return list;
	}

	public BlogVo getVo(String id) {
		BlogVo vo = sqlSession.selectOne("blog.getvo",id);
		return vo;
	}

	public void update(BlogVo vo) {
		sqlSession.update("blog.update",vo);
	}

	public int getUserNo(String id) {
		return sqlSession.selectOne("blog.getuserno",id);
	}

}
