package jdbc05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConnectionFactory;

public class EmpDao {

    public static void main(String[] args) {
    		String sql = "SELECT EMPNO,ENAME,JOB "
    				+ "FROM EMP "
    				+ "ORDER BY EMPNO ASC";
    		
    		ArrayList<Emp> empList = new ArrayList<>();
    		
    		try(Connection connection = ConnectionFactory.getConnection();
    				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    			
    			try(ResultSet resultSet = preparedStatement.executeQuery()) {
    				while(resultSet.next()) {
//    					System.out.printf("EMPNO: %d, ENAME: %s, JOB: %s%n",
//    							resultSet.getInt("EMPNO"), 
//    							resultSet.getString("ENAME"), 
//    							resultSet.getString("JOB"));
    					Emp emp = new Emp();
    					emp.setEmpNo(resultSet.getInt("EMPNO"));
    					emp.setEName(resultSet.getString("ENAME"));
    					emp.setJob(resultSet.getString("JOB"));
    					
    					empList.add(emp);
    					
    				}
    				
    				for(Emp emp : empList) {
    					System.out.printf(
    							"EMPNO: %d, ENAME: %s, JOB: %s%n",
    							emp.getEmpNo(),
    							emp.getEName(),
    							emp.getJob());
    				}
    			}
    		}catch(SQLException e) {
    				System.out.println(e.getMessage());
    		}
    }
}
