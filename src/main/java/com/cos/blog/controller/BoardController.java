package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = org.springframework.data.domain.Sort.Direction.DESC ) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));
		// /WEB-INF/views/index.jsp <- /WEB-INF : prefix, .jsp : suffix
		return "index"; //viewResolver 작동
	}
	
	@GetMapping("/board/{id}")
	public String findByID(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
