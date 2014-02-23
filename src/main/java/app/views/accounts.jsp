<%@page import="java.util.List"%>
<%@page import="app.models.OwnerBean"%>
<%@page import="app.models.AccountBean"%>
<%@page
	import="static net.sf.rubycollect4j.RubyCollections.newRubyArray"%>
<%@page import="net.sf.rubycollect4j.block.TransformBlock"%>

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

		<select multiple name="ownerId">
			<%
			  for (OwnerBean owner : (List<OwnerBean>) request.getAttribute("owners")) {
			%>
			<option value="<%=owner.getId()%>"><%=owner.getFirstName()%>
				<%=owner.getLastName()%></option>
			<%
			  }
			%>
		</select>

		<hr>

		<h1>Accounts</h1>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>SSN</th>
					<th>Owners</th>
				</tr>
			</thead>
			<tbody>
				<%
				  for (AccountBean account : (List<AccountBean>) request
				      .getAttribute("accounts")) {
				%>
				<tr>
					<td><%=account.getAccountNumber()%></td>
					<td><%=account.getRoutingNumber()%></td>
					<%
					  String name =
					        newRubyArray(account.getOwners()).map(
					            new TransformBlock<OwnerBean, String>() {

					              @Override
					              public String yield(OwnerBean owner) {
					                return owner.getFirstName() + " " + owner.getLastName();
					              }

					            }).join(", ");
					%>
					<td><%=name%></td>
				</tr>
				<%
				  }
				%>
			</tbody>
		</table>

		<hr>

		<div>
			Account Number: <input name="accountNumber" /><br>
			<!--  -->
			Routing Number: <input name="routingNumber" /><br>
			<!--  -->
			<input type="submit" value="Create Account" />
		</div>
	</form>

	<hr>

	<a href="${pageContext.request.contextPath}">Home</a>
</body>
</html>