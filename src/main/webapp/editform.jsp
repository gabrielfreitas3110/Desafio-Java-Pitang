<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
</head>
<body>
	<%@ page import="bean.User, dao.*, java.util.*"%>

	<%
	UserDao userDao = DaoFactory.createUserDao();
	String id = request.getParameter("id");
	User user = userDao.findById(Integer.parseInt(id));
	%>
	<button onclick="window.location.href='index.jsp'">Home</button>
	<button onclick="window.location.href='viewusers.jsp'">All Users</button>
	<h2>Editing User</h2>
	<form action="edituser.jsp" method="post">
		<table>
			<tr>
				<td>Id: </td>
				<td><input type="hidden" name="id" value="<%=user.getId()%>"/></td>
			</tr>
			<tr>
				<td>Name: </td>
				<td><input type="text" name="name" value="<%=user.getName()%>" /></td>
			</tr>
			<tr>
				<td>Email: </td>
				<td><input type="email" name="email" value="<%=user.getEmail()%>" /></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type="password" name="password"
					value="<%=user.getPassword()%>" /></td>
			</tr>
			<tr>
				<td>Cell phones: </td>
				<td><input type="text" name="cellphone"
					value="<%=user.getCellphones()%>" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Edit user"></td>
			</tr>
		</table>
	</form>
</body>
</html>
