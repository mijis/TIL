package day0311;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class LoginDAO_Day0311 {
	//0311 ¼÷Á¦
		public String useCallableStatement(LoginVO lVO) throws SQLException {
			String name =null;
			
			Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			
			DbConnection dc = DbConnection.getInstance();
			
			try {
				con = dc.getConn();
				cstmt = con.prepareCall("{ call login_proc(?,?,?) }");
				
				cstmt.setString(1, lVO.getId());
				cstmt.setString(2, lVO.getPasswd());
				
				cstmt.registerOutParameter(3, OracleTypes.CURSOR);
				
				cstmt.execute();
				
				rs = (ResultSet)cstmt.getObject(3);
				
				if(rs.next()) {
					name=rs.getString("name");
				}
				
			}finally {
				dc.close(null, cstmt, con);
			}
			
			return name;
		}//useCallableStatement
}
