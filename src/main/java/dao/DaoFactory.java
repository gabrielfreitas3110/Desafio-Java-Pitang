package dao;

import dao.impl.UserDaoJDBC;
import db.DB;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
}
