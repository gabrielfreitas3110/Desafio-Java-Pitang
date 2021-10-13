<%@ page import="dao.*"%>
<jsp:useBean id="u" class="bean.User"></jsp:useBean>
<jsp:useBean id="c" class="bean.Cellphone"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>
<jsp:setProperty property="*" name="c"/>

<%
	UserDao userDao = DaoFactory.createUserDao();
	CellphoneDao cellphoneDao = DaoFactory.createCellphoneDao();
	userDao.insert(u);
	c.setUser_id(u.getId());
	cellphoneDao.insert(c);
	response.sendRedirect("viewusers.jsp");
%>
