package day0308;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0307.DbConnection;

public class DAO {
	
	private static DAO d;
	
	private DAO() {
	}//DAO

	public static DAO getInstance() {
		if(d==null) {
			d = new DAO();
		}
		return d;
	}//getInstance

	
	public List<String> selectMaker() throws SQLException {
		List<String> listMaker =new  ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConnection dc = DbConnection.getInstance();
		
		try {
			//1.
			//2.
				con = dc.getConn();
			//3.
				StringBuilder selectMaker = new StringBuilder();
				selectMaker
				.append("	select	distinct	maker	")
				.append("	from	car_maker			");
				
				pstmt = con.prepareStatement(selectMaker.toString());
			//4.
			//5.
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					listMaker.add(rs.getString("maker"));
				}//end while			
				
		}finally {
			//6.
			dc.close(rs, pstmt, con);
			
		}//end finally
		
		return listMaker;
	}//selectMaker
	
	
	public List<String> selectALL(String maker) throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConnection dc = DbConnection.getInstance();
		
		try {
			//1.
			//2.
				con = dc.getConn();
			//3.
				StringBuilder select = new StringBuilder();
				select
				.append("	select		cc.COUNTRY, cc.MAKER, cma.MODEL, cmo.CAR_YEAR, cmo.PRICE, cmo.CAR_OPTION 	")
				.append("	from		CAR_MAKER cma	")
				.append("	inner join  CAR_COUNTRY cc	")
				.append("	on          cc.maker = cma.maker	")
				.append("	inner join  CAR_MODEL  cmo	")
				.append("	on          cma.model = cmo.model	")
				.append("	where		cc.maker = ?	");

				pstmt = con.prepareStatement(select.toString());
			//4.
				pstmt.setString(1, maker);
			//5.
				rs = pstmt.executeQuery();
				StringBuilder row = new StringBuilder();
				while(rs.next()) {
					row
					.append(rs.getString("COUNTRY")).append("\t")
					.append(rs.getString("MAKER")).append("\t")
					.append(rs.getString("MODEL")).append("\t")
					.append(rs.getInt("CAR_YEAR")).append("\t")
					.append(rs.getInt("PRICE")).append("\t")
					.append(rs.getString("CAR_OPTION")).append("\n");
					
					list.add(row.toString());
				}//end while

				
		}finally {
			//6.
			dc.close(rs, pstmt, con);
		}
		
		return list;
	}
	
	
}//class
