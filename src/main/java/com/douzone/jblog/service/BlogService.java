package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public Map<String, Object> getListTitle(String search, int page) {
		
		
		
		int totalcount = blogDao.getCount(search);
		int totalpage;
		int pagecount;
		
		if(totalcount % 10 == 0)
		{
			totalpage = (totalcount / 10);
			pagecount = 10;
		}
		else
		{
			totalpage = (totalcount / 10) + 1;
			pagecount = totalcount % 10;
		}
		
		int start_page = (page / 6)*5 + 1;
		int end_page = start_page + 4;
		
		if(totalpage < end_page)
			end_page = totalpage;
		
		int[] pageList = {page, start_page, end_page, totalpage, pagecount};
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("page", (page-1)*10);
		
		List<BlogVo> list = blogDao.getListTitle(map);
		
		map.clear();
		map.put("list", list);
		map.put("pagelist",pageList);
		map.put("search", search);
		
		return map;
	}

	public Map<String, Object> getListId(String search, int page) {
		int totalcount = blogDao.getCount(search);
		int totalpage;
		int pagecount;
		
		if(totalcount % 10 == 0)
		{
			totalpage = (totalcount / 10);
			pagecount = 10;
		}
		else
		{
			totalpage = (totalcount / 10) + 1;
			pagecount = totalcount % 10;
		}
		
		int start_page = (page / 6)*5 + 1;
		int end_page = start_page + 4;
		
		if(totalpage < end_page)
			end_page = totalpage;
		
		int[] pageList = {page, start_page, end_page, totalpage, pagecount};
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("page", (page-1)*10);
		
		List<BlogVo> list = blogDao.getListId(map);
		
		map.clear();
		map.put("list", list);
		map.put("pagelist",pageList);
		map.put("search", search);
		
		return map;
	}
}
