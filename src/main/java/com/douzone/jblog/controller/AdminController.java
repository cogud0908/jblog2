package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.security.Auth;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private AdminService adminService;
		
	@Auth
	@RequestMapping("/{id}")
	public String admin(@PathVariable String id, Model model) {
		
		BlogVo vo = adminService.getVo(id);
		model.addAttribute("blogVo",vo);

		return "/blog/blog-admin-basic";
	}
	
	@Auth
	@PostMapping("/update/{id}")
	public String update(@PathVariable(value="id") Optional<String> id,
			@ModelAttribute BlogVo blogVo, @RequestParam(value="upload-logo") MultipartFile multipartFile,
			Model model) {
		
		int user_no = adminService.getUserNo(id.get());
		
		String profile = fileuploadService.restore(multipartFile);
		blogVo.setLogo(profile);
		blogVo.setUser_no(user_no);
		
		adminService.update(blogVo);
		
		return "redirect:/"+id.get();
	}
	
	@Auth
	@GetMapping("/category/{id}")
	public String category(@PathVariable String id, Model model) {
		
		List<CategoryVo> list = adminService.categoryGetList(id);
		System.out.println("카테고리 리스트 : "+list);
		model.addAttribute("list",list);
		
		return "/blog/blog-admin-category";
	}
		
	@Auth
	@GetMapping("/write/{id}")
	public String writeForm(@PathVariable String id, Model model) {
	
		List<CategoryVo> list = adminService.getCategorylist(id);
		model.addAttribute("categorylist",list);
		return "/blog/blog-admin-write";
	}
	
	@Auth
	@PostMapping("/write/{id}")
	public String write(@PathVariable String id, @ModelAttribute PostVo postVo) {
				
		adminService.postWrite(id, postVo);
		
		return "redirect:/admin/"+id;
	}
	
}
