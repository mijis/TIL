package day0307;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton Pattern을 도입한 클래스!
 * Connection 얻기, 끊기
 * @author user
 *
 */
public class DbConnection {
	private static DbConnection dc;
	
	
	/**
	 * 클래스 외부에서 객체화되는 것을 막는다.
	 */
	private DbConnection() {
	}//DbConnection
	
	/**
	 * DbConnection 객체를 반환하는 일
	 * @return
	 */
	public static DbConnection getInstance() {
		
		if(dc==null) {
			dc = new DbConnection();
		}
		
		return dc;
	}
	
	public Connection getConn() throws SQLException {
		Connection con = null;
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2. 커넥션 얻기
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		con = DriverManager.getConnection(url,id,pass);
		
		
		
		return con;
		
	}
	
	
	public Statement getStatement() throws SQLException {
		Statement stmt =null;
		stmt = getConn().createStatement();
		return stmt;
	}

	/**
	 * DBMS 연결 종료
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws SQLException
	 */
	public void close(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		if(con!=null) {con.close();}
	}
	
}
