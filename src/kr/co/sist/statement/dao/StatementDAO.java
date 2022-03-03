package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.statement.vo.CpEmp4InsertVO;
import kr.co.sist.statement.vo.CpEmp4SelectAllVO;
import kr.co.sist.statement.vo.CpEmp4SelectOneVO;
import kr.co.sist.statement.vo.CpEmp4UpdateVO;

/**
 * DAO(Data Access Object)
 * method명은 쿼리명으로 설정한다
 * @author user
 */
public class StatementDAO {
	public StatementDAO() {}
	
	/**
	 * DBMS와 연결한 객체를 반환하는 일
	 * @return Connection
	 * @throws SQLException
	 */
	private Connection getDBConnection() throws SQLException {
		Connection con=null;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2. Connection 엳기
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getDBConnection
	
	
	public void insertCpEmp4(CpEmp4InsertVO ceiVO) throws SQLException {
		Connection con = null;
		Statement stmt=null;
		
		try {
		//1. 드라이버 로딩
		//2. 커넥션 얻기
			con = getDBConnection();
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
		//4. 쿼리 수행 후 결과 얻기
			
			StringBuilder insertCpEmp = new StringBuilder();
			insertCpEmp
			.append("insert into cp_emp4(empno,ename,job,sal,comm) values(")
			.append(ceiVO.getEmpno()).append(", '")
			.append(ceiVO.getEname()).append("', '")
			.append(ceiVO.getJob()).append("', ")
			.append(ceiVO.getSal()).append(", ")
			.append(ceiVO.getComm()).append(")");
			
//			int cnt=stmt.executeUpdate(insertCpEmp.toString());
//			System.out.println(cnt + "건 입력 성공");
			//insert는 한 건 아니면 예외이므로 변수에 저장할 필요가 없다
			stmt.executeUpdate(insertCpEmp.toString());
			
			
		}finally {
			//5. 연결 끊기
			if(stmt!=null) {stmt.close();}
			if(con!=null) {con.close();}
		}
			
	}//insertCpEmp4
	
	/**
	 * 사원정보 변경<br>
	 * 사원번호와 맞는 사원의 직무, 연봉, 보너스를 변경하는 일
	 * @param ceuVO 변경할 사원정보를 가진 객체
	 * @return 변경된 행의 수
	 * @throws SQLException
	 */
	public int updateCpEmp4(CpEmp4UpdateVO ceuVO) throws SQLException{
		int rowCnt=0;
		Connection con=null;
		Statement stmt=null;
		try {
			//1. 드라이버 로딩
			//2. 커넥션 얻기
			con=getDBConnection();
			//3. 쿼리문 생성객체 얻기
			stmt=con.createStatement();
			//4. 쿼리문 수행 후 결과 얻기	
			StringBuilder updateCpEmp=new StringBuilder();
			updateCpEmp
			.append("	update	cp_emp4	")
			.append("	set job='")
			.append(ceuVO.getJob()).append("',	")
			.append("	sal=").append(ceuVO.getSal()).append(",")
			.append("	comm=").append(ceuVO.getComm())
			.append(" where empno=").append(ceuVO.getEmpno());
			
			rowCnt=stmt.executeUpdate(updateCpEmp.toString());
			
		}finally {
			//5. 연결 끊기
			if(stmt!=null) {stmt.close();}
			if(con!=null) {con.close();}
		}
		
		return rowCnt;
	}//updateCpEmp4
	
	public int deleteCpEmp4(int empno) throws SQLException {
		int rowCnt=0;
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			//1. 드라이버로딩
			//2. 커넥션 얻기
				con=getDBConnection();
			//3. 쿼리문 생성 객체 얻기
				stmt=con.createStatement();
			//4. 쿼리문 수행 후 결과
				StringBuilder deleteCpEmp=new StringBuilder();
				deleteCpEmp.append("delete from cp_emp4 where empno=").append(empno);
		
				rowCnt=stmt.executeUpdate(deleteCpEmp.toString());
				
		}finally {
			//5. 연결 끊기
			if(stmt!=null) {stmt.close();}
			if(con!=null) {con.close();}
		}
		
		return rowCnt;
	}//deleteCpEmp4
	
	public CpEmp4SelectOneVO selectCpEmp4(int empno) throws SQLException {
		CpEmp4SelectOneVO cesoVo = null;
		
		return cesoVo;
	}//selectCpEmp4
	
	public List<CpEmp4SelectAllVO> selectCpEmp4() throws SQLException{
		List<CpEmp4SelectAllVO> list = new ArrayList<CpEmp4SelectAllVO>();
		
		Connection con = getDBConnection();
		Statement stmt = con.createStatement();
		
		String sql = "select * from dept";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("레코드 존재함? " + rs.next());
		System.out.println("레코드 존재함? " + rs.next());
		System.out.println("레코드 존재함? " + rs.next());
		System.out.println("레코드 존재함? " + rs.next());
		System.out.println("레코드 존재함? " + rs.next());
		
		
		
		return list;
	}//selectCpEmp4
	
	//Debug용 method
	public static void main(String[] args) throws SQLException {
		//단위 Test
		StatementDAO sd = new StatementDAO();

		sd.selectCpEmp4();
		
	} 
	
	
}
