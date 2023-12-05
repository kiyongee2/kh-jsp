/**
 * 회원 가입 유효성 검사
 */
function checkForm(){
	//name 속성 변수
	let form = document.member;
	let id = form.uid.value;
	let pw1 = form.passwd.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value; 
	
	//정규 표현식
	let regexPw1 = /[0-9]+/;         //숫자 1개 이상
	let regexPw2 = /[a-zA-Z]+/;      //영어 1개 이상
	let regexPw3 = /[~!@#$%^&*()_=]+/; //특수문자만
	let regexName = /^[가-힣]+$/      //한글만
	
	//이름은 한글만 입력되도록 처리
	if(id.length < 5 || id.length > 12){
		alert("아이디는 5 ~ 12자까지 입력해주세요");
		form.id.select();
		return false;
	}else if(pw1.length < 8 || !regexPw1.test(pw1)
				|| !regexPw2.test(pw1) || !regexPw3.test(pw1)){
		alert("비밀번호는 영문자, 숫자, 특수문자 포함 8자 이상 입력");
		form.passwd.select();
		return false;
	}else if(pw1 != pw2){
		alert("비밀번호를 동일하게 입력해주세요");
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
