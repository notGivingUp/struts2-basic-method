<%@page import="model.User"%>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Update</h3>
<%User selectedUser=(User) request.getAttribute("selectedUser"); %>

<form action="edit" method="post">
 ID: <input type="text" name="user.iduser" value="<%=selectedUser.getIduser() %>">
<br/>Username: <input type="text" name="user.username" value="<%=selectedUser.getUsername() %>">
<br/>Password: <input type="text" name="user.password" value="<%=selectedUser.getPassword() %>">

<input type="submit" value="update">
<!-- <p style="color:red"> dung textfield se bi loi </p> -->
</form>
</body>
</html>