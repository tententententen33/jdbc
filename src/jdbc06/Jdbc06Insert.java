package jdbc06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;

public class Jdbc06Insert {
	public static void main(String[] args) {
//		String selectSql = "SELECT EMPNO, ENAME, JOB "
//				+ "FROM EMP "
//				+ "WHERE EMPNO = ?";
		String sql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO "
				+ "FROM EMP";
		
//		String insertSql = "INSERT INTO EMP (EMPNO, ENAME, JOB) VALUES(?, ?, ?)";
		
		try(Connection connection = ConnectionFactory.getConnection();
				PreparedStatement selectStatement = connection.prepareStatement(sql)) {
			
//			selectStatement.setInt(1, 7839);
			
			try(ResultSet resultSet = selectStatement.executeQuery()) {
				while(resultSet.next()) {
					System.out.printf(
							"EMPNO: %d, ENAME: %s, JOB: %s, MGR: %d, HIREDATE: %s, SAL: %d, COMM: %d, DEPTNO: %d%n",
							resultSet.getInt("EMPNO"), 
							resultSet.getString("ENAME"),
							resultSet.getString("JOB"),
							resultSet.getInt("MGR"),
							resultSet.getDate("HIREDATE"),
							resultSet.getInt("SAL"),
							resultSet.getInt("COMM"),
							resultSet.getInt("DEPTNO"));
				}
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
//		try(Connection connection = ConnectionFactory.getConnection();
//				PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
//			
//			insertStatement.setInt(1, 9999);
//			insertStatement.setString(2, "YAMADA");
//			insertStatement.setString(3, "MANAGER");
//			
//			int rowcount = insertStatement.executeUpdate();
//			System.out.println(rowcount);
//			
//		}catch(SQLException e) {
//			System.out.println(e.getMessage());
//		}
	}
}
