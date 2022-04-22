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
			//1. JNDI ��� ��ü ����
			Context ctx = new InitialContext();
			//2. DBCP�� ã�� DataSource�� ���
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dbcp");
			//3. Ŀ�ؼ� ���
			con = ds.getConnection();
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
		return con;
	}
	
	/**
	 * �Էµ� ���̵� TEST_MEMBER���̺� �����ϴ���
	 * @return
	 */
	public boolean selected(String id) throws SQLException {
		boolean flag = false; 
		//boolean���� �޾ƿ��� �ȴٴ� ����� ��������! list ��ȯ�� �ʿ� ����
		
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
			
			flag=rs.next();//��ȸ����� ������ true
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
