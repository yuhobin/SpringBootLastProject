package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeMainController {
	@GetMapping("/recipe/main")
	public String recipe_main(Model model) {
		model.addAttribute("main_html", "recipe/main/home");
		return "recipe/main/main";
	}
}
