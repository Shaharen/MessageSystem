<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script>
		alert('로그아웃!');
/* 		location.href = "main.jsp";
 */	</script>
</head>
<body>
	<%  session.invalidate();
		response.sendRedirect("main.jsp");
	%>
</body>
</html>