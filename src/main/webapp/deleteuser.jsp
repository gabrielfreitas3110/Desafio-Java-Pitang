<%@ page import="dao.*"%>
<jsp:useBean id="u" class="bean.User"></jsp:useBean>
<jsp:useBean id="c" class="bean.Cellphone"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>
<jsp:setProperty property="*" name="c"/>

<%
	UserDao userDao = DaoFactory.createUserDao();
	CellphoneDao cellphoneDao = DaoFactory.createCellphoneDao();
	cellphoneDao.deleteByUserId(u.getId());
	userDao.delete(u.getId());
	c.setUser_id(u.getId());
	response.sendRedirect("viewusers.jsp");
%>
