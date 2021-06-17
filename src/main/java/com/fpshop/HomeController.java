package com.fpshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpshop.model.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService service;
	
	@GetMapping("/home")
	public void home() {
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "exception", required = false) String exception,
						Model model)
	{
		model.addAttribute("error",error);
		model.addAttribute("exception",exception);
		return "login";
	}

	@PostMapping("/login")
	public String loginProc(UserEntity p) {
		System.out.println(p.getUser_id());
		return "redirect:/home";
	}

	@GetMapping("/logout")
	public String logout() {return "logout"; }

	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String join(UserEntity p) {
		int result = service.mangoJoin(p);
		System.out.println("result : " + result);
		return "redirect:/login";
	}

	@GetMapping("/index")
	public String index() {return "index";}

}
