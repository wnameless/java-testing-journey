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
	<form method="POST">
		<h1>Owners</h1>
		<h6>Allow Multiple Selection</h6>
		<select multiple name="ownerId">
			<c:forEach var="owner" items="${owners}">
				<option value="${owner.id}"><c:out
						value="${owner.firstName} ${owner.lastName}" /></option>
			</c:forEach>
		</select>

		<hr>

		<h1>Accounts</h1>
		<table>
			<thead>
				<tr>
					<th>AccountNumber</th>
					<th>RoutingNumber</th>
					<th>Owners</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td><c:out value="${account.accountNumber}" /></td>
						<td><c:out value="${account.routingNumber}" /></td>
						<td><c:forEach var="owner" items="${account.owners}">
								<p>
									<c:out value="${owner.firstName}" />
									<c:out value="${owner.lastName}" />
								</p>
							</c:forEach></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<hr>

		<div>
			<p>
				Account Number: <input name="accountNumber" />
			</p>
			<p>
				Routing Number: <input name="routingNumber" /><br>
			</p>
			<input type="submit" value="Create Account" />
		</div>
	</form>

	<hr>

	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>