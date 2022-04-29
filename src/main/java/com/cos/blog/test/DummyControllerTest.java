package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.hibernate.annotations.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController // data를 return 해주는 Controller
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // 부모함수인 Exception을 걸어도 되지만 Exception 특정이 힘듬
			return "삭제에 실패했습니다. 해당 id는 DB에 없습니다";
		}
		return "삭제되었습니다" + id;
	}
	
	//save함수는 insert에서 보통 사용함, Transactional annotation을 사용하면 더티체킹을 함
	@Transactional //함수가 끝나면서 return 하게 되면 자동으로 commit 됨 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id " + id);
		System.out.println("password " + requestUser.getPassword());
		System.out.println("email " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패했습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		return user;
	}
	
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = org.springframework.data.domain.Sort.Direction.DESC ) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		
		return users;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// userRepository에서 id를 찾을 때 null 값이 아니면 orElseGet 이하는 실행되지 않음.
		// 반대로 null 값을 찾아온다면 프로그램에 문제가 생기기 때문에 findbyid().get으로 가져오지 않고
		// orElseGet 함수로 User interface 새로운 객체를 하나 생성해서 리턴함. 빈 객체
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				// TODO Auto-generated method stub
//				return new User();
//			}
//		});
		// 람다식 ( 매우 간편하게 Supplier를 익명으로 대체 할 수 있음
//		User user = userRepository.findById(id).orElseThrow(()-> {
//			return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹 브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter라는 애가 응답시 자동 작동
		// 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson  라이브러리를 호출해서 
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) { //String username, String password, String email
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
}
