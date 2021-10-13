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
	Boolean logged = false;
	for(User u : users) {
		if(u.getLogged()) {
			logged = true;
		}
	}
	if(!logged) {
		response.sendRedirect("index.jsp");
	}
	%>
	<button onclick="window.location.href='logout.jsp'">Logout</button>
	<h2>List of Users</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Password</th>
			<th>Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.getId()}</td>
				<td>${user.getName()}</td>
				<td>${user.getEmail()}</td>
				<td>${user.getPassword()}</td>
				<td class="hidden">${user.getCellphones()}</td>
				<td><a href="editform.jsp?id=${user.getId()}">Edit</a></td>
				<td><a href="deleteuser.jsp?id=${user.getId()}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="addform.jsp">Add new User</a>
</body>
</html>