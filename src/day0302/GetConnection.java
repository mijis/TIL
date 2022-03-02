package day0302;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC�� ����� Connection ���
 * @author user
 *
 */
public class GetConnection {

	public GetConnection() throws SQLException {
		//1. ����̹� �ε�(e:/dev/driver/ojdbc8.jar)
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) { //Compile exception (Checked Exception)
			//Runtime Exception(Unchecked Exception)
			e.printStackTrace();
		}//end catch
		
		
		//2. �ε��� ����̹��� ����Ͽ� Connection ���
			String url="jdbc:oracle:thin:@localhost:1521:orcl"; //localhost, 127.0.0.1
			String id= "scott";
			String pass= "tiger";
			
			Connection con =null;
			Statement stmt = null;
			try {
				con = DriverManager.getConnection(url, id, pass);
				System.out.println("DB���Ἲ�� : "+con);
				
				//3. ������ ������ü ���
				stmt=con.createStatement();
				
				//4. ������ ���� �� ��� ���
				String dname="���ߺ�";
				String insertQuery="insert into cp_dept(deptno, dname, loc) values(99,'"+dname+"','����')";
				int cnt = stmt.executeUpdate(insertQuery);
				System.out.println(insertQuery+"�� " + cnt + "�� �߰��Ǿ����ϴ�.");
				
			}finally {
				//5. ���� ����
				if(stmt!=null) {stmt.close();}
				if(con!=null) {con.close();}
			}//end finally
		
	}//GetConnection
	
	public static void main(String[] args) {
		try {
			new GetConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch

	}//main

}//class
