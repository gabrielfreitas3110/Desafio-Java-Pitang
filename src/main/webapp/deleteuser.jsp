<%@ page import="dao.*, bean.User, java.util.*"%>
<jsp:useBean id="u" class="bean.User"></jsp:useBean>
<jsp:useBean id="c" class="bean.Cellphone"></jsp:useBean>
<jsp:setProperty property="*" name="u" />
<jsp:setProperty property="*" name="c" />

<%
UserDao userDao = DaoFactory.createUserDao();
User user = userDao.findById(u.getId());
if (user == null) {
	response.sendRedirect("index.jsp");
} else if (!user.getLogged()) {
	response.sendRedirect("delerror.jsp");
} else {
	CellphoneDao cellphoneDao = DaoFactory.createCellphoneDao();
	cellphoneDao.deleteByUserId(u.getId());
	userDao.delete(u.getId());
	c.setUser_id(u.getId());
	response.sendRedirect("viewusers.jsp");
}
%>
