package sut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;

public class JdbcTest02 {
	public static void main(String[] args) {
		String selectSql = "SELECT EMPNO, ENAME, SAL "
				+ "FROM EMP "
				+ "WHERE EMPNO = ?";
		
		String updateSql = "UPDATE EMP SET SAL = SAL + ? WHERE EMPNO = ?";
		
		System.out.println("SQL: " + selectSql);
		
		try(Connection connection = ConnectionFactory.getConnection();
				PreparedStatement selectStatement = connection.prepareStatement(selectSql);
				PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
			selectStatement.setInt(1, 7934);
			
			updateStatement.setInt(1, 100);
			updateStatement.setInt(2, 7934);
			
			int rowCount = updateStatement.executeUpdate();
			System.out.println("更新件数:" + rowCount);
			try(ResultSet resultSet = selectStatement.executeQuery()) {
				while(resultSet.next()) {
					System.out.printf("EMP_NO: %d, NAME: %s, SAL: %d",
							resultSet.getInt("EMPNO"),
							resultSet.getString("ENAME"),
							resultSet.getInt("SAL"));
					
				}

			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
