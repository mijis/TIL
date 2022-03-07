package day0307;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 *
 */
public class SelectTableDAO {

	private static SelectTableDAO stDAO;
	
	private SelectTableDAO() {
	}//SelectTableDAO
	
	public static SelectTableDAO getinstance() {
		if(stDAO==null) {
			stDAO = new SelectTableDAO();
		}
		return stDAO;
	}//getinstance
	
	
	public List<String> comboList() throws SQLException  {
		String tableName=null;
		List<String> list=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		DbConnection dc = DbConnection.getInstance();
		
		try {
			//1. 드라이버 로딩
			//2. 커넥션 얻기
				con=dc.getConn();
			//3. 쿼리문 생성객체 얻기
				tableName = "select TABLE_NAME from tabs";
				pstmt=con.prepareStatement(tableName);
				
			//4. 쿼리문 실행 후 결과 얻기
				rs = pstmt.executeQuery();
				list = new ArrayList<String>();
				
				while(rs.next()) {
					list.add(rs.getString("TABLE_NAME"));
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. 연결 끊기
				dc.close(rs, pstmt, con);
		}
		return list;
	}//comboList
	
	
	public List<TableVO> selectTable(String tableName) throws SQLException {
		List<TableVO> list = null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		DbConnection dc = DbConnection.getInstance();
		
		try {
			//1. 드라이버 로딩
			//2. 커넥션 얻기
				con=dc.getConn();
			//3. 쿼리문 생성객체 얻기
				String table = "select column_name, data_type, data_length from user_tab_cols ";
				pstmt=con.prepareStatement(table);
				
			//4. 쿼리문 실행 후 결과 얻기
				rs = pstmt.executeQuery();
				TableVO tVO = null;
				
				
				list = new ArrayList<TableVO>();
				
				while(rs.next()) {
					tVO =new TableVO(rs.getString("column_name"),rs.getString("data_type"),rs.getInt("data_length"));
					list.add(tVO);
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. 연결 끊기
				dc.close(rs, pstmt, con);
		}
		
		return list ;
	}
	
	
	
	
	public static void main(String[] args)  {
		stDAO=SelectTableDAO.getinstance();
	}//main
	
}//class
