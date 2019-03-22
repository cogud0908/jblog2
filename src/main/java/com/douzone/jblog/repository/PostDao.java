package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void write(PostVo postVo) {
		sqlSession.insert("post.insert",postVo);
		
		int category_no = postVo.getCategory_no();
		sqlSession.update("category.updateCount",category_no);
		
	}

	public List<PostVo> getlist(int cateNo) {
		List<PostVo> postlist = sqlSession.selectList("post.getlist",cateNo);
		return postlist;
	}

	public PostVo getPost(int categoryNo, int postNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", postNo);
		map.put("category_no", categoryNo);
		
		PostVo Vo = sqlSession.selectOne("post.getPostOne",map);
		return Vo;
	}

	public Integer getPostNo(int category_no) {
		return sqlSession.selectOne("post.getPostFirstNo",category_no);
	}

	
}
