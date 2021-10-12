package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import dao.UserDao;
import db.DB;
import db.DbException;

public class UserDaoJDBC implements UserDao {

	private Connection con = null;

	public UserDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public List<User> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM tb_user ORDER BY name");
			rs = ps.executeQuery();
			List<User> users = new ArrayList<>();
			while (rs.next()) {
				User obj = instantiateUser(rs);
				users.add(obj);
			}
			return users;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public User findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM tb_user WHERE Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				User obj = instantiateUser(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(User obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Integer id, User obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	private User instantiateUser(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setPassword(rs.getString("password"));
		return obj;
	}
}