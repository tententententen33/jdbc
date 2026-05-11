package jdbc08;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;

public class Jdbc08Transaction {

	public static void main(String[] args) {

		String updateMinusSql = "UPDATE EMP SET SAL = SAL - ? WHERE EMPNO = ?";
		String updatePlusSql = "UPDATE EMP SET SAL = SAL + ? WHERE EMPNO = ?";
		String selectSql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO "
				+ "FROM EMP WHERE EMPNO = ?";

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement minusStatement = connection.prepareStatement(updateMinusSql);
				PreparedStatement plusStatement = connection.prepareStatement(updatePlusSql);
				PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {

			connection.setAutoCommit(false);
			System.out.println("autoCommit = " + connection.getAutoCommit());

			boolean simulateError = false;
			
			try {
				minusStatement.setInt(1, 200);
				minusStatement.setInt(2, 7934);
				int row1 = minusStatement.executeUpdate();
				System.out.println("更新件数:" + row1);
				
				if(simulateError) {
					throw new SQLException("教材用の疑似エラー");
				}

				plusStatement.setInt(1, 100); 
				plusStatement.setInt(2, 7934); 
				int row2 = plusStatement.executeUpdate();  
				System.out.println("更新件数: " + row2); 
				
				connection.commit(); 
				System.out.println("commitを実行しました"); 
			} catch (SQLException e) {
				connection.rollback();
				System.out.println("理由:" + e.getMessage());
			} finally {
				connection.setAutoCommit(true);
			}

			//ここから下は、SELECT結果を表示する部分
			selectStatement.setInt(1, 7934);

			try (ResultSet resultSet = selectStatement.executeQuery()) {
				while (resultSet.next()) {
					System.out.printf(
							"更新後 -> EMPNO: %d, ENAME: %s, JOB: %s, MGR: %d, HIREDATE: %s, SAL: %d, COMM: %d, DEPTNO: %d%n",
							resultSet.getInt("EMPNO"),
							resultSet.getString("ENAME"),
							resultSet.getString("JOB"),
							resultSet.getInt("MGR"),
							resultSet.getDate("HIREDATE").toString(),
							resultSet.getInt("SAL"),
							resultSet.getInt("COMM"),
							resultSet.getInt("DEPTNO"));

				}
			}
		} catch (SQLException e) {
			System.out.println("トランザクション処理でエラーが発生しました");
			System.out.println(e.getMessage());
		}

	}

}
