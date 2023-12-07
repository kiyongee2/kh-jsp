
function checkForm(){
	//name 속성 변수
	let form = document.member;
	let id = form.uid.value;
	let pw1 = form.passwd.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value; 
	
	//정규 표현식
	let regexPw1 = /[0-9]+/;         
	let regexPw2 = /[a-zA-Z]+/;      
	let regexPw3 = /[~!@#$%^&*()_=]+/; 
	let regexName = /^[가-힣]/      
	
	if(id.length < 5 || id.length > 12){
		alert("아이디는 5 ~ 12자까지 입력해주세요");
		id.select();
		return false;
	}else if(pw1.length < 7 || !regexPw1.test(pw1)
				|| !regexPw2.test(pw1) || !regexPw3.test(pw1)){
		alert("영문자, 숫자, 특수문자 포함 7자 이상 입력");
		passwd.select();
		return false;
	}else if(pw1 != pw2){
		alert("비밀번호를 동일하게 입력해주세요");
		passwd2.select();
		return false;
	}else if(!regexName.test(name)){
		alert("이름은 숫자로 시작할수 없습니다.");
		name.focus();
		return false;
	}else{
	    form.submit();
	}
}
