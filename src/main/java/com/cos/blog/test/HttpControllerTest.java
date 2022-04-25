package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	@GetMapping("/http/get")
	public String getTest(Member m) { //?id=1&username=ssar&password=1234&email=ssar@nate.com
		return "get 요청 : " + m.getId() + ", " + m.getUsername()+ ", " + m.getPassword() + ", " + m.getEmail();
	}
//	@PostMapping("/http/post")
//	public String postTest(Member m) { //x-www-form-urlencoded, body에 form형식으로 post하는 방식
//		return "post 요청 :" + m.getId() + ", " + m.getUsername()+ ", " + m.getPassword() + ", " + m.getEmail();
//	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //application/json, MessageConverter(스프링부트)
		return "post 요청 :" + m.getId() + ", " + m.getUsername()+ ", " + m.getPassword() + ", " + m.getEmail();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 :" + m.getId() + ", " + m.getUsername()+ ", " + m.getPassword() + ", " + m.getEmail();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
