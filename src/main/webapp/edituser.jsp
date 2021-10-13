<%@ page import="dao.*"%>
<jsp:useBean id="u" class="bean.User"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
	UserDao userDao = DaoFactory.createUserDao();
	userDao.update(u);
	response.sendRedirect("viewusers.jsp");
%>
