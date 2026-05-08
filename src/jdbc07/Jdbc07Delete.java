package jdbc07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;

public class Jdbc07Delete {

	public static void main(String[] args) {
		String selectSql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO "
				+ "FROM EMP WHERE EMPNO = ?";

		String deleteSql = "DELETE EMP WHERE EMPNO = ?";
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement selectStatement = connection.prepareStatement(selectSql);
					PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

			selectStatement.setInt(1, 7782);
			System.out.println("削除前の確認");
			printMember(selectStatement);

			deleteStatement.setInt(1, 7782);
			System.out.println("削除後の確認");
			int rowCount = deleteStatement.executeUpdate();
			System.out.println(rowCount + "件削除しました");
			printMember(selectStatement);
		} catch (SQLException e) {
			System.out.println("DELETE 実行時にエラーが発生しました");
			System.out.println(e.getMessage());
		}

	}

	private static void printMember(PreparedStatement preparedStatement) throws SQLException {
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				System.out.printf(
						"削除 -> EMPNO: %d, ENAME: %s, JOB: %s, MGR: %d, HIREDATE: %s, SAL: %d, COMM: %d, DEPTNO: %d%n",
						resultSet.getInt("EMPNO"),
						resultSet.getString("ENAME"),
						resultSet.getString("JOB"),
						resultSet.getInt("MGR"),
						resultSet.getDate("HIREDATE").toString(),
						resultSet.getInt("SAL"),
						resultSet.getInt("COMM"),
						resultSet.getInt("DEPTNO"));
			} else {
				System.out.println("該当データは存在しません");
			}
		}
	}

}
