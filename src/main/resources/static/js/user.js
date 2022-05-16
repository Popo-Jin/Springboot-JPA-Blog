let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{ //function(){} 대신 ()=>{} 사용하는 이유 this를 바인딩하기 위해
			this.save();
		});
		$("#btn-login").on("click", ()=>{ //function(){} 대신 ()=>{} 사용하는 이유 this를 바인딩하기 위해
			this.login();
		});
	},
	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}
		
		console.log(data);
		//회원가입 수행 요청
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body 데이터, data는 JS오브젝트, JSON.stringify(data)는 JSON문자열
			contentType: "application/json; charset=utf-8", //body데이터 타입
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 JSON으로 왔으면 JS오브젝트로 변환 (기본적으로 문자열로 옴)
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			console.log(resp)
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
		login: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}

		$.ajax({
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data), //http body 데이터, data는 JS오브젝트, JSON.stringify(data)는 JSON문자열
			contentType: "application/json; charset=utf-8", //body데이터 타입
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 JSON으로 왔으면 JS오브젝트로 변환 (기본적으로 문자열로 옴)
		}).done(function(resp) {
			alert("로그인이 완료되었습니다.");
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init()

