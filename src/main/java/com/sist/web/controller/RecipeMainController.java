package com.sist.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.MemberService;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeMainController {
	private final MemberService mService;
	
	@GetMapping("/recipe/main")
	public String recipe_main(Authentication auth, Model model) {
		boolean isLogin=auth!=null
				&& auth.isAuthenticated()
				&& auth.getPrincipal()
						.toString()
						.equals("annonymousUser")==false;
		model.addAttribute("isLogin", isLogin);
		if(isLogin) {
			String username=auth.getName();
			MemberVO vo=mService.findByUsername(username);
			model.addAttribute("vo", vo);
		}
		model.addAttribute("main_html", "recipe/main/home");
		return "recipe/main/main";
	}
}
