package jdbc07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;


public class Jdbc07Update {

	public static void main(String[] args) {
		String selectSql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO "
				+ "FROM EMP WHERE EMPNO = ?";
		
		String updateSql = "UPDATE EMP SET SAL = ? WHERE EMPNO = ?";


        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement selectStatement = connection.prepareStatement(selectSql);
                		PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

        	
            selectStatement.setInt(1, 7782);
            updateStatement.setInt(1, 3000);
            updateStatement.setInt(2, 7782);
            int rowCount = updateStatement.executeUpdate();
            System.out.println(rowCount + "件更新しました");

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
        }catch(SQLException e) {
        		System.out.println(e.getMessage());
        }
	}

}

