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
	
	
	public List<String> selectAllTab() throws SQLException  { //0308���� : method�̸��� �������̰� ����
		String tableName=null;
		List<String> list=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		DbConnection dc = DbConnection.getInstance();
		
		try {
			//1. ����̹� �ε�
			//2. Ŀ�ؼ� ���
				con=dc.getConn();
			//3. ������ ������ü ���
				tableName = "select tname from tab";
				pstmt=con.prepareStatement(tableName);
				
			//4. ������ ���� �� ��� ���
				rs = pstmt.executeQuery();
				list = new ArrayList<String>();
				
				while(rs.next()) {
					list.add(rs.getString("tname"));
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. ���� ����
				dc.close(rs, pstmt, con);
		}
		return list;
	}//selectAllTab
	
	
	public List<TableVO> selectAllColumn(String tableName) throws SQLException {//0308 �������� �̸��ֱ�
		List<TableVO> list = null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		DbConnection dc = DbConnection.getInstance();
		
		try {
			//1. ����̹� �ε�
			//2. Ŀ�ؼ� ���
				con=dc.getConn();
			//3. ������ ������ü ���
				String table = "select column_name, data_type, data_length from user_tab_cols where table_name=?";
				//0308 ���� where table=?�� ����
				pstmt=con.prepareStatement(table);
				
				//���ε庯��
				pstmt.setString(1, tableName);
				
			//4. ������ ���� �� ��� ���
				
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
			//5. ���� ����
				dc.close(rs, pstmt, con);
		}
		
		return list ;
	}
	
	
	
	
	public static void main(String[] args)  {
		stDAO=SelectTableDAO.getinstance();
	}//main
	
}//class
