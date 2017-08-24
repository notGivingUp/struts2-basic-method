<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function doDownload(id, x) {
	
	document.forms[0].iduser.value = id;
	document.forms[0].event.value = x;
	alert(document.forms[0].event.value);
	alert(document.forms[0].iduser.value);
	document.forms[0].submit();
}

</script>
</head>
<body>
<%  String str = (String) session.getAttribute("username");
	System.out.println(str);
	if (session.getAttribute("username")==null)
		response.sendRedirect("/struts2/index.jsp");
	%>
	<h2>All User</h2>
	<a href=logout>Log out</a>
	<s:if test="userList.size() > 0">
	<s:form action="eventAction">
			<table border="1px" cellpadding="8px">
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th>Option</th>
				</tr>
				<s:iterator value="userList" status="userStatus">
					<tr>
						<td><s:property value="iduser" /></td>
						<td><s:property value="username" /></td>
						<td>
							<s:a href="#" onclick="doDownload(%{iduser},'edit')" >edit</s:a>
							<s:a href="#" onclick="doDownload(%{iduser},'delete')" >delete</s:a>
						</td>
					</tr>
				</s:iterator>
			</table>
			<s:hidden name="iduser" value="" />
			<s:hidden name="event" value="" />
		</s:form>
	</s:if>
	<br />
	<br />
</body>
</html>