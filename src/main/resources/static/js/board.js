let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){} 대신 ()=>{} 사용하는 이유 this를 바인딩하기 위해
			this.save();
		});
	},
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //http body 데이터, data는 JS오브젝트, JSON.stringify(data)는 JSON문자열
			contentType: "application/json; charset=utf-8", //body데이터 타입
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init()

