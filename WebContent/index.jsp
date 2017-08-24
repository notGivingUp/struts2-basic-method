<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Struts 2 + Hibernate integration example</h1>

<form action="login" method="post">
	Username:<br/><input type="text" name="user.username"><br/>
	Password:<br/><input type="password" name="user.password"><br/>
	<input type="submit" value="Log in">
	</form>
Neu ban chua co tai khoan, xin moi dang ki <a href="register.jsp">tai day</a>
</body>
</html>