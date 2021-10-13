<%@ page import="bean.*, dao.*, java.util.*"%>
<jsp:useBean id="u" class="bean.User"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
	UserDao userDao = DaoFactory.createUserDao();
	User user = new User();
	List<User> users = userDao.findAll();
	Boolean logged = false;
	for(User thisUser : users) {
		if(thisUser.getEmail().equals(u.getEmail()) && thisUser.getPassword().equals(u.getPassword())) {
			thisUser.setLogged(true);
			logged = true;
			userDao.update(thisUser);
			user = userDao.findById(thisUser.getId());
		} 
	}
	if(logged) {
		response.sendRedirect("loginsuccess.jsp");
	} else {
		response.sendRedirect("loginfail.jsp");
	}
%>
