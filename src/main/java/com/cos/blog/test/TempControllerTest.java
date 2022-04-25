package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		//파일리턴경로 : src/main/resources/static (정적 파일 기본 경로, JSP파일 X)
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("tempjsp()");
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		return "test";
	}
}
