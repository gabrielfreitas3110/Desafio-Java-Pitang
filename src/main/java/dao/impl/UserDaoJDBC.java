package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Cellphone;
import bean.User;
import dao.UserDao;
import db.DB;
import db.DbException;
import db.DbIntegrityException;

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
			ps = con.prepareStatement(
					"SELECT tb_user.*, tb_cellphone.id AS cell_id, tb_cellphone.ddd, tb_cellphone.number, tb_cellphone.type, tb_cellphone.user_id "
					+ "FROM tb_user INNER JOIN tb_cellphone "
					+ "ON tb_user.id = tb_cellphone.user_id");
			rs = ps.executeQuery();
			Map<Integer, User> userMap = new HashMap<>();
			Map<Integer, Cellphone> cellMap = new HashMap<>();
			
			while(rs.next()) {
				User user = userMap.get(rs.getInt("id"));
				if(user == null) {
					user = instantiateUser(rs);
					userMap.put(rs.getInt("id"), user);
				}
				Cellphone cellphone = cellMap.get(rs.getInt("cell_id"));
				if(cellphone == null) {
					cellphone = instantiateCellphone(rs);
					cellMap.put(rs.getInt("cell_id"), cellphone);
				}
			}

			List<User> users = new ArrayList<>(userMap.values());
			List<Cellphone> cellphones = new ArrayList<>(cellMap.values());
			
			for(User u : users) {
				for(Cellphone c : cellphones) {
					if(u.getId().equals(c.getUser_id())) {
						u.addCellphone(c);
					}
				}
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
			ps = con.prepareStatement(
					"SELECT tb_user.*, tb_cellphone.id AS cell_id, tb_cellphone.ddd, tb_cellphone.number, tb_cellphone.type, tb_cellphone.user_id "
					+ "FROM tb_user INNER JOIN tb_cellphone "
					+ "ON tb_user.id = tb_cellphone.user_id WHERE tb_cellphone.user_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			User user = null;
			while (rs.next()) {
				if(user == null) {
					user = instantiateUser(rs);
				}
				Cellphone cell = instantiateCellphone(rs);
				if(!user.getCellphones().contains(cell)) {
					cell = instantiateCellphone(rs);
					user.addCellphone(cell);
				}
			}
			if(user != null)
				return user;
			else
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
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO " 
					+ "tb_user (name, email, password) " 
					+ "VALUES (?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setString(3, obj.getPassword());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(User obj) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE tb_user "
					+ "SET name = ?, email = ?, password = ? " 
					+ "WHERE id = ?");
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setString(3, obj.getPassword());
			ps.setInt(4, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void delete(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM tb_user WHERE id = ?");
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows == 0 ) {
				throw new DbException("Id doesn't exists in table tb_user");
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	private User instantiateUser(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setPassword(rs.getString("password"));
		return obj;
	}

	private Cellphone instantiateCellphone(ResultSet rs) throws SQLException {
		Cellphone obj = new Cellphone();
		obj.setId(rs.getInt("cell_id"));
		obj.setDdd(rs.getInt("ddd"));
		obj.setNumber(rs.getString("number"));
		obj.setType(rs.getString("type"));
		obj.setUser_id(rs.getInt("user_id"));
		return obj;
	}
}