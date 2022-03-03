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
 * method���� ���������� �����Ѵ�
 * @author user
 */
public class StatementDAO {
	public StatementDAO() {}
	
	/**
	 * DBMS�� ������ ��ü�� ��ȯ�ϴ� ��
	 * @return Connection
	 * @throws SQLException
	 */
	private Connection getDBConnection() throws SQLException {
		Connection con=null;
		
		//1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2. Connection ����
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
		//1. ����̹� �ε�
		//2. Ŀ�ؼ� ���
			con = getDBConnection();
		//3. ������ ������ü ���
			stmt = con.createStatement();
		//4. ���� ���� �� ��� ���
			
			StringBuilder insertCpEmp = new StringBuilder();
			insertCpEmp
			.append("insert into cp_emp4(empno,ename,job,sal,comm) values(")
			.append(ceiVO.getEmpno()).append(", '")
			.append(ceiVO.getEname()).append("', '")
			.append(ceiVO.getJob()).append("', ")
			.append(ceiVO.getSal()).append(", ")
			.append(ceiVO.getComm()).append(")");
			
//			int cnt=stmt.executeUpdate(insertCpEmp.toString());
//			System.out.println(cnt + "�� �Է� ����");
			//insert�� �� �� �ƴϸ� �����̹Ƿ� ������ ������ �ʿ䰡 ����
			stmt.executeUpdate(insertCpEmp.toString());
			
			
		}finally {
			//5. ���� ����
			if(stmt!=null) {stmt.close();}
			if(con!=null) {con.close();}
		}
			
	}//insertCpEmp4
	
	/**
	 * ������� ����<br>
	 * �����ȣ�� �´� ����� ����, ����, ���ʽ��� �����ϴ� ��
	 * @param ceuVO ������ ��������� ���� ��ü
	 * @return ����� ���� ��
	 * @throws SQLException
	 */
	public int updateCpEmp4(CpEmp4UpdateVO ceuVO) throws SQLException{
		int rowCnt=0;
		Connection con=null;
		Statement stmt=null;
		try {
			//1. ����̹� �ε�
			//2. Ŀ�ؼ� ���
			con=getDBConnection();
			//3. ������ ������ü ���
			stmt=con.createStatement();
			//4. ������ ���� �� ��� ���	
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
			//5. ���� ����
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
			//1. ����̹��ε�
			//2. Ŀ�ؼ� ���
				con=getDBConnection();
			//3. ������ ���� ��ü ���
				stmt=con.createStatement();
			//4. ������ ���� �� ���
				StringBuilder deleteCpEmp=new StringBuilder();
				deleteCpEmp.append("delete from cp_emp4 where empno=").append(empno);
		
				rowCnt=stmt.executeUpdate(deleteCpEmp.toString());
				
		}finally {
			//5. ���� ����
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
		System.out.println("���ڵ� ������? " + rs.next());
		System.out.println("���ڵ� ������? " + rs.next());
		System.out.println("���ڵ� ������? " + rs.next());
		System.out.println("���ڵ� ������? " + rs.next());
		System.out.println("���ڵ� ������? " + rs.next());
		
		
		
		return list;
	}//selectCpEmp4
	
	//Debug�� method
	public static void main(String[] args) throws SQLException {
		//���� Test
		StatementDAO sd = new StatementDAO();

		sd.selectCpEmp4();
		
	} 
	
	
}
