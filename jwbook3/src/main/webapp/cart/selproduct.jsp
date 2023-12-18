<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	#container{width: 800px; margin: 0 auto; text-align: center;}
</style>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
	    session.setAttribute("sessionName", username);  //세션 발급
	%>
	<div id="container">
		<h2>상품 선택</h2>
		<hr>
		<p><%=session.getAttribute("sessionName") %>님이 로그인한 상태입니다.</p>
		<form action="addproduct.jsp" method="post" name="form1">
			<select name="product" id="product" onchange="addProduct()">
				<option>--상품 선택--</option>
				<option value="사과">사과</option>
				<option value="바나나">바나나</option>
				<option value="토마토">토마토</option>
			</select>
			<!-- <input type="submit" value="추가"> -->
		</form>
		<p><button onclick="location.href='cart.jsp' ">장바구니</button></p>
	</div>
	<script>
		let addProduct = function(){
			let product = document.getElementById("product").value;
			//alert(product + " 선택");
			document.form1.submit();
		}
		//화살표 함수
		/*const addProduct = () => {
			let product = document.getElementById("product").value;
			document.form1.submit();
		}*/
	</script>
</body>
</html>