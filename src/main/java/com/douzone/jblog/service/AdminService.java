package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class AdminService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public void update(BlogVo blogVo) {
		blogDao.update(blogVo);
	}

	public BlogVo getVo(String id) {
		BlogVo vo = blogDao.getVo(id);
		return vo;
	}
	
	public int getUserNo(String id) {
		int no = blogDao.getUserNo(id);
		return no;
	}
	
	public List<CategoryVo> categoryGetList(String id) {
		int no = blogDao.getUserNo(id);
		
		return categoryDao.getList(no);
	}
	
	public List<CategoryVo> getCategorylist(String id) { 
		int no = blogDao.getUserNo(id);
	  
		List<CategoryVo> list = categoryDao.getCategory(no);
	  
		return list; 
	}
	 
	 
	public List<CategoryVo> ajaxList(String id) {
		int user_no =blogDao.getUserNo(id);
				
		return categoryDao.getList(user_no);		
	}

	public void ajaxDelete(int no) {
		categoryDao.ajaxDelete(no);
	}

	public CategoryVo ajaxInsert(String id, CategoryVo categoryVo) {
				
		int user_no =blogDao.getUserNo(id);
		categoryVo.setUser_no(user_no);
		CategoryVo result = categoryDao.ajaxInsert(categoryVo);
		
		return result;
	}
	
	public void postWrite(String id, PostVo postVo) {
		
		int no =blogDao.getUserNo(id);
		int category_no = 0;
		
		List<CategoryVo> list = categoryDao.getCategory(no);
		
		for (int i = 0; i < list.size(); i++) {
			if(postVo.getCategory().equals(list.get(i).getName())) {
				category_no = list.get(i).getNo();
			}
		}
		
		postVo.setCategory_no(category_no);		
		postDao.write(postVo);
	}

	public List<PostVo> postgetList(int cateNo) {
		List<PostVo> list = postDao.getlist(cateNo);
		return list;
	}

	public PostVo getPost(int categoryNo, int postNo) {
		PostVo postVo = postDao.getPost(categoryNo, postNo);
		return postVo;
	}

	public Integer getCategoryNo(String id) {
		int user_no = blogDao.getUserNo(id);
		
		return categoryDao.getCategoryNo(user_no);
	}

	public Integer getPostNo(Integer cateNo) {
		
		Integer result = postDao.getPostNo(cateNo.intValue());
		
		return result;
	}
}
