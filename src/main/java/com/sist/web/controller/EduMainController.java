package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EduMainController {
	@GetMapping("/edu/main")
	public String edu_main(Model model) {
		model.addAttribute("main_html", "edu/main/home");
		return "edu/main/main";
	}
}
