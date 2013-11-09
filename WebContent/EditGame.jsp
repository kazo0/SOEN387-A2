<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="models.Game"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Game gm = (Game) request.getSession().getAttribute("game");
	%>
	
	<form action="GameController" method="post">
			<table>
				<tr>
					<td><label>Name :</label></td>
					<td><input name="Name" type="text" value="<%= gm.getName() %>"/> </td>
				</tr>
				<tr>
					<td><label>Description :</label></td>
					<td><input name="Description" type="text" value="<%= gm.getDescription() %>" /> </td>
				</tr>
				<tr>
					<td><label>Price :</label></td>
					<td><input name="Price" type="text" value="<%= gm.getPrice() %>" /> </td>
				</tr>
				<tr>
					<td><label>Quantity :</label></td>
					<td><input name="Quantity" type="text" value="<%= gm.getQty() %>" /> </td>
				</tr>
			</table>
			<input type="hidden" name="option" value="edit"/>
	</form>
</body>
</html>