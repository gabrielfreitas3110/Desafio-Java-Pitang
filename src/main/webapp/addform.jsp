<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<%@ page import="bean.User, dao.*, java.util.*"%>

	<button onclick="window.location.href='viewusers.jsp'">All
		Users</button>
	<h2>Registry new user</h2>

	<form action="adduser.jsp" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Cell Phone</td>
			</tr>
			<tr>
				<td>ddd:</td>
				<td><input type="number" name="ddd" /></td>
			</tr>
			<tr>
				<td>number:</td>
				<td><input type="text" name="number" /></td>
			</tr>
			<tr>
				<td>type:</td>
				<td><input type="text" name="type" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="add user" /></td>
			</tr>
		</table>
	</form>
</body>
</html>