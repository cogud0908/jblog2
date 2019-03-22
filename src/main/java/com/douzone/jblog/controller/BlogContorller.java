package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;


@Controller
@RequestMapping("/{id:(?!assets|uploads).*}") // /{"id:?!(assets).*}")
public class BlogContorller {
		
	@Autowired
	private AdminService adminService;
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String main(@PathVariable String id,
					   @PathVariable Optional<Integer> categoryNo,
					   @PathVariable Optional<Integer> postNo,
					   Model model) {
			
		Integer cateNo = 0;
		Integer poNo = 0;
				
		if(categoryNo.toString().equals("Optional.empty")) {
			cateNo = adminService.getCategoryNo(id);
		} else {
			cateNo = categoryNo.get();
		}
		
		if(postNo.toString().equals("Optional.empty")) {
			poNo = adminService.getPostNo(cateNo);
			
			if(poNo == null) {
				poNo = 0;
			}
			
		} else {
			poNo = postNo.get();
		}
		
		System.out.println("cateNo : "+cateNo);
		System.out.println("poNo : "+poNo);
		
		BlogVo blogVo = adminService.getVo(id);
		List<CategoryVo> categorylist = adminService.categoryGetList(id);
		List<PostVo> postlist = adminService.postgetList(cateNo.intValue());
		PostVo postVo = adminService.getPost(cateNo.intValue(),poNo.intValue());
		
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("siteId",id);
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("postlist",postlist);
		model.addAttribute("postVo",postVo);
		
		return "/blog/blog-main";
	}
			
}
