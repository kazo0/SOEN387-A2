<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<form action="GameController" method="post">
		<table>
			<tr>
				<td><label>Name :</label></td>
				<td><input name="Name" type="text" /> </td>
			</tr>
			<tr>
				<td><label>Description :</label></td>
				<td><input name="Description" type="text" /> </td>
			</tr>
			<tr>
				<td><label>Price :</label></td>
				<td><input name="Price" type="text" /> </td>
			</tr>
			<tr>
				<td><label>Quantity :</label></td>
				<td><input name="Quantity" type="text" /> </td>
			</tr>
		</table>
		<input type="hidden" name="option" value="add"/>
	</form>
</body>
</html>