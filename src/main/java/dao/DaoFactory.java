package dao;

import dao.impl.CellphoneDaoJDBC;
import dao.impl.UserDaoJDBC;
import db.DB;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}

	public static CellphoneDao createCellphoneDao() {
		return new CellphoneDaoJDBC(DB.getConnection());
	}
}