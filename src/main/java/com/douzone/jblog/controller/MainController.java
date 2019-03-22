package com.douzone.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.BlogService;

@Controller
public class MainController {

	@Autowired
	private BlogService blogService;
		
	@RequestMapping({"","/main"})
	public String index(
			@RequestParam(value="witch", required=false) String witch,
			@RequestParam(value="search", required=false) String search, 
			@RequestParam(value="page", required=false) String page,
			Model model) {
		if(page == null) page = "1";
		if(search == null) search = "";
		if(witch == null) witch = "blog-title";
		
		Map<String, Object> resultMap = null;
		
		if(witch.equals("blog-title"))
		resultMap = blogService.getListTitle(search ,Integer.parseInt(page));
		else if(witch.equals("blog-user"))
		resultMap = blogService.getListId(search, Integer.parseInt(page));
		
		model.addAllAttributes(resultMap);
				
		return "/main/index";
	}
}
