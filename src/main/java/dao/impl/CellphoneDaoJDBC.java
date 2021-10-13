package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Cellphone;
import dao.CellphoneDao;
import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class CellphoneDaoJDBC implements CellphoneDao {

	private Connection con = null;

	public CellphoneDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public List<Cellphone> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM tb_cellphone");
			rs = ps.executeQuery();
			List<Cellphone> cellphones = new ArrayList<>();
			while (rs.next()) {
				Cellphone obj = instantiateCellphones(rs);
				cellphones.add(obj);
			}
			return cellphones;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Cellphone findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM tb_cellphone WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Cellphone obj = instantiateCellphones(rs);
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
	public void insert(Cellphone obj) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO " 
					+ "tb_cellphone (ddd, number, type, user_id) " 
					+ "VALUES (?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, obj.getDdd());
			ps.setString(2, obj.getNumber());
			ps.setString(3, obj.getType());
			ps.setInt(4, obj.getUser_id());
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
	public void update(Integer id, Cellphone obj) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE tb_cellphone "
					+ "SET ddd = ?, number = ?, type = ? " 
					+ "WHERE id = ?");
			ps.setInt(1, obj.getDdd());
			ps.setString(2, obj.getNumber());
			ps.setString(3, obj.getType());
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
			ps = con.prepareStatement("DELETE FROM tb_cellphone WHERE id = ?");
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows == 0 ) {
				throw new DbException("Id doesn't exists in table tb_cellphone");
			}
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	private Cellphone instantiateCellphones(ResultSet rs) throws SQLException {
		Cellphone obj = new Cellphone();
		obj.setId(rs.getInt("id"));
		obj.setDdd(rs.getInt("ddd"));
		obj.setNumber(rs.getString("number"));
		obj.setType(rs.getString("type"));
		return obj;
	}
}