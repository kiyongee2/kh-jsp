/**
     회원 가입시 유효성 검사
 */
function checkMember(){
	let form = document.member;
	let id = form.id.value;
	let pw1 = form.passwd.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value; 
	
	//정규 표현식
	let regexPw1 = /[0-9]+/;      //숫자만
	let regexPw2 = /[a-zA-Z]+/;   //영어만
	let regexPw3 = /[~!@#$%^&*()_=]+/; //특수문자만
	let regexName = /^[가-힣]+$/   //한글만
	
	//이름은 한글만 입력되도록 처리
	if(id.length < 4 || id.length > 15){
		alert("아이디는 4 ~ 15자까지 입력해주세요");
		id.select();
		return false;
	}else if(pw1.length < 8 || !regexPw1.test(pw1)
				|| !regexPw2.test(pw1) || !regexPw3.test(pw1)){
		alert("비밀번호는 영문자, 숫자, 특수문자 포함 8자 이상입니다.");
		passwd.select();
		return false;
	}else if(pw1 != pw2){
		alert("비밀번호가 동일하게 입력해 주세요");
		form.passwd2.select();
		return false;
	}else if(name=="" || !regexName.test(name)){
		alert("이름은 한글로 입력해주세요");
		form.name.focus();
		return false;
	}else{
	    form.submit();
	}
}

// 아이디 중복 검사
function checkId(){
	//alert("test");
	if(document.member.id.value == ""){
		alert("아이디를 입력해주세요");
		return false;
	}
	
	let mid = $('#id').val();  //입력된 아이디
	$.ajax({
		type: "post",
		dataType: "text",
		url: "duplicatedid", //http://localhost:8080/idcheck과 동일
		data: {id: mid},
		success: function(data){
			if(data == 'usable'){
				$('#btnChk').attr("value", "Y");
				$('#check').text('사용할 수 있는 ID입니다');
			}else{ //data == 'not_usable'
				$('#check').text('이미 가입된 ID입니다');
			}
		},
		error: function(){
			alert("에러 발생!!");
		}
	});
}













