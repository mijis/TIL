package day0307;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public List<String> selectAllTab() throws SQLException  { //0308수정 : method이름은 직관적이게 주자
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
				tableName = "select tname from tab";
				pstmt=con.prepareStatement(tableName);
				
			//4. 쿼리문 실행 후 결과 얻기
				rs = pstmt.executeQuery();
				list = new ArrayList<String>();
				
				while(rs.next()) {
					list.add(rs.getString("tname"));
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. 연결 끊기
				dc.close(rs, pstmt, con);
		}
		return list;
	}//selectAllTab
	
	
	public List<TableVO> selectAllColumn(String tableName) throws SQLException {//0308 직관적인 이름주기
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
				String table = "select column_name, data_type, data_length from user_tab_cols where table_name=?";
				//0308 수정 where table=?을 잊음
				pstmt=con.prepareStatement(table);
				
				//바인드변수
				pstmt.setString(1, tableName);
				
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
