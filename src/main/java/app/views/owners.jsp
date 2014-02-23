<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<c:forEach var="owner" items="${owners}">
				<tr>
					<td><c:out value="${owner.firstName} ${owner.lastName}" /></td>
					<td><c:out value="${owner.ssn}" /></td>
					<td><c:out value="${owner.email}" /></td>
					<td><c:out value="${owner.phone}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<hr>

	<form method="POST">
		<p>
			First Name: <input name="firstName" /><br>
		</p>
		<p>
			Last Name: <input name="lastName" /><br>
		</p>
		<p>
			SSN: <input name="ssn" /><br>
		</p>
		<p>
			Email: <input name="email" /><br>
		</p>
		<p>
			Phone: <input name="phone" /><br>
		</p>
		<input type="submit" value="Create Owner" />
	</form>

	<hr>

	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>