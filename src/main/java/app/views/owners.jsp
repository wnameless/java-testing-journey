<%@page import="java.util.List"%>
<%@page import="app.models.OwnerBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java Testing Journey</title>
</head>
<body>
	<h1>Owners</h1>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>SSN</th>
				<th>Email</th>
				<th>Phone</th>
			</tr>
		</thead>
		<tbody>
			<%
			  for (OwnerBean owner : (List<OwnerBean>) request.getAttribute("owners")) {
			%>
			<tr>
				<td><%=owner.getFirstName()%> <%=owner.getLastName()%></td>
				<td><%=owner.getSsn()%></td>
				<td><%=owner.getEmail()%></td>
				<td><%=owner.getPhone()%></td>
			</tr>
			<%
			  }
			%>
		</tbody>
	</table>

	<hr>

	<form method="POST">
		First Name: <input name="firstName" /><br>
		<!--  -->
		Last Name: <input name="lastName" /><br>
		<!--  -->
		SSN: <input name="ssn" /><br>
		<!--  -->
		Email: <input name="email" /><br>
		<!--  -->
		Phone: <input name="phone" /><br>
		<!--  -->
		<input type="submit" value="Create Owner" />
	</form>

	<hr>

	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>