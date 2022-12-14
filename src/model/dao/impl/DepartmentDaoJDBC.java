package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department dpt) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)"
					, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dpt.getName());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					dpt.setId(id);
				}
				DB.closeResulSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department dpt) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE department SET Name=? WHERE Id=?");
			ps.setString(1, dpt.getName());
			ps.setInt(2, dpt.getId());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE Id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE Id=?");
			st.setInt(1, id);	
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
	
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResulSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department");
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			while (rs.next()) {
				list.add(instantiateDepartment(rs));
			}
	
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResulSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
		return dep;
	}

}
