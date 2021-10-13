<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success</title>
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
	<p>You can't edit this user!!</p>
	<jsp:include page="viewusers.jsp"></jsp:include>
</body>
</html>