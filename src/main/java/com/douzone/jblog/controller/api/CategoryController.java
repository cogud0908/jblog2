package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.dto.JSONResult;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.vo.CategoryVo;

@Controller("CategoryApiController")
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private AdminService adminService;
	
	@ResponseBody
	@RequestMapping("/list/{id}")
	public JSONResult ajaxList(@PathVariable String id) {
		List<CategoryVo> list = adminService.ajaxList(id);
								
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult ajaxDelete(@RequestParam(value="no") String no) {
		adminService.ajaxDelete(Integer.parseInt(no));
		
		return JSONResult.success("success");
	}
	
	@ResponseBody
	@RequestMapping("/insert/{id}")
	public JSONResult ajaxInsert(@PathVariable String id, @ModelAttribute CategoryVo categoryVo) {
		CategoryVo result = adminService.ajaxInsert(id,categoryVo);
		
		return JSONResult.success(result);
	}
}
