<%@ page import="bean.User, dao.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
UserDao userDao = DaoFactory.createUserDao();
List<User> users = userDao.findAll();
request.setAttribute("users", users);
Boolean logged = false;
for (User u : users) {
	if (u.getLogged()) {
		u.setLogged(false);
		userDao.update(u);
	}
}
response.sendRedirect("index.jsp");
%>