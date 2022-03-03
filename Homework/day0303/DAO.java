package day0303_work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	
	public void insertName(InsertVO iVO) throws SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con = null;
		Statement stmt = null;
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			stmt = con.createStatement();
			
			StringBuilder insertSql = new StringBuilder();
			insertSql.append("insert into name_work(name) values('")
				.append(iVO.getName())
				.append("')");
				System.out.println(insertSql);
			
				stmt.executeUpdate(insertSql.toString());
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();	
		} finally {
			if(stmt!=null) {stmt.close();}
			if(con!=null) {con.close();}
			
		}
	}
}
