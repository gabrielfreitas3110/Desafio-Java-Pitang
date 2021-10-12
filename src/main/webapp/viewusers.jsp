<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
</head>
<body>
	<%@ page import="bean.User, dao.*, java.util.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<%
	UserDao userDao = DaoFactory.createUserDao();
	List<User> users = userDao.findAll();
	request.setAttribute("users", users);
	%>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Password</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.getId()}</td>
				<td>${user.getName()}</td>
				<td>${user.getEmail()}</td>
				<td>${user.getPassword()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>