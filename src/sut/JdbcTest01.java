package sut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;

public class JdbcTest01 {
	public static void main(String[] args) {
		String sql = "SELECT EMPLOYEE_ID,LAST_NAME "
				+ "FROM EMPLOYEES "
				+ "WHERE LAST_NAME = 'King'";
		
		System.out.println("[実行されるSQL]:" + sql);
		System.out.println("------------------------------");
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			System.out.println("SQLを実行する準備ができました");
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					System.out.printf("EMPLOYEE_ID = %d, LAST_NAME = %s%n",resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("LAST_NAME"));
				}
			}
		}catch (SQLException e) {
			System.out.println("SELECT実行時にエラーが発生しました");
			System.out.println(e.getMessage());
		}
	}
}
