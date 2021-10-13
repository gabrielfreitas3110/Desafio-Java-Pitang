<%@ page import="bean.*, dao.*, java.util.*"%>
<jsp:useBean id="u" class="bean.User"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
	UserDao userDao = DaoFactory.createUserDao();
	List<User> users = userDao.findAll();
	boolean logged = false;
	for(User list : users) {
		if(list.getEmail().equals(u.getEmail()) && list.getPassword().equals(u.getPassword())) {
			logged = true;
		} 
	}
	if(logged) {
		response.sendRedirect("loginsuccess.jsp");
	} else {
		response.sendRedirect("loginfail.jsp");
	}
%>
