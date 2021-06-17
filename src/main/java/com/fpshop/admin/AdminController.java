package com.fpshop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/alogin")
	public String login() {
		return "admin/alogin";
	}

	@GetMapping("/productList")
	public String productList() {return "admin/productList";}

	@GetMapping("/productManage")
	public String productManage() {return "admin/productManage";}

	@GetMapping("/productUpload")
	public String productUpload() {return "admin/productUploadMod";}
}
