/**
 * 상품 등록 유효성검사
 */

 let checkProduct = function(){
	 //alert("등록");
	 let pid = document.getElementById("pid");
	 let pname = document.getElementById("pname");
	 let price = document.getElementById("price");
	 let pstock = document.getElementById("pstock");
	 
	 //정규 표현식
	 let regexPid = /P[0-9]{4,11}/
	 
	 if(!regexPid.test(pid.value)){
		 alert("상품코드는 P와 숫자를 조합하여 5~12자로 입력");
		 pid.select();
		 return false;
	 }else if(pname.value.length < 4 || pname.value.length > 20 ){
		 alert("상품명은 4~20자 입력");
		 pname.select();
		 return false; 
	 }else if(price.value.length == 0 || isNaN(price.value)
	    		|| price.value < 0){
		 alert("가격은 숫자만 입력");
		 price.select();
		 return false; 
	 }else if(pstock.value.length == 0 || isNaN(pstock.value)
	 			|| pstock.value < 0){
		 alert("재고수는 숫자만 입력");
		 pstock.select();
		 return false; 
	 }
	 else{
		 document.newProduct.submit();
	 }
 }