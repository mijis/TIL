package day0422;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import day0418.EmpVO;

public class memberDAO {
	
private static memberDAO mDAO;
	
	private memberDAO() {
	}
	
	public static memberDAO getInstance() {
		if(mDAO==null) {
			mDAO = new memberDAO();
		}
		return mDAO;
	}
	
	private Connection getConnection() throws SQLException {
		Connection con=null;
		
		try {
			//1. JNDI 사용 객체 생성
			Context ctx = new InitialContext();
			//2. DBCP를 찾아 DataSource를 얻고
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dbcp");
			//3. 커넥션 얻기
			con = ds.getConnection();
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 입력된 아이디가 TEST_MEMBER테이블에 존재하는지
	 * @return
	 */
	public boolean selected(String id) throws SQLException {
		boolean flag = false; 
		//boolean으로 받아오면 된다는 사실을 잊지말자! list 반환할 필요 없음
		
		Connection con=null;
		PreparedStatement pstmt=null; 
		ResultSet rs=null; 
		
		try {
			con=getConnection();
			String sql= "select id from test_member where id = ?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
		
//			if(rs.next()) {
//				flag=true; 
//			}
			
			flag=rs.next();//조회결과가 있으면 true
		}finally {
			dbClose(rs, pstmt, con);
		}
		
		return flag;
	}
	
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException{
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(con!=null) {con.close();}
		
	}
	
}
