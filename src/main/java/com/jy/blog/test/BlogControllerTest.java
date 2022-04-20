package com.jy.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //스프링이 com.jy.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아니고 
							//특정 annotation이 붙어 있는 클래스 파일들을 new 해서(IOC) 스프링 컨테이너에 관리해준다.
public class BlogControllerTest {
	// http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
