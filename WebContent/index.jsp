<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="model.Game" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assignment 2</title>
</head>
<body>
	<form action="Test">
	
	
	
		<h1>Games in the Database</h1>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
			
				<%
				Game[] Items = (Game[]) request.getSession().getAttribute("items");
				for (int i = 0; i < Items.length; i++) {
				%>
				<tr>
	   				<td><label><%= Items[i].getID()%></label></td>
	   				<td><label><%= Items[i].Name%></label></td>
	   				<td><label><%= Items[i].Description%></label></td>
	   				<td><label><%= Items[i].Price%></label></td>
	   				<td><label><%= Items[i].Qty%></label></td>
				</tr>
				<% } %>
		</table>
		
		
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
			<tr>
				<td>
					Action Type
				</td>
				<td>
					<select>
						<option value="1">New</option>
						<option value="2">Edit</option>
						<option value="3">Delete</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label>Enter ID of the Item to be Edited or Deleted</label>
				</td>
				<td>
					<input type="text" name="ID">
				</td>
			</tr>
			
		</table>
		
		<input type="submit" value ="Submit" />
	</form>
</body>
</html>