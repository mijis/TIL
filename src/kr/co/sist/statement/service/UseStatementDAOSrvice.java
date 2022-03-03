package kr.co.sist.statement.service;

import java.awt.Menu;

import javax.swing.JOptionPane;

import kr.co.sist.statement.dao.StatementDAO;

/**
 * 업무로직을 처리하면서 필요에 따라 DB작업을 사용하는 클래스<br>
 * XXXService 클래스의 method명은 업무의 용어로 작성한다 (절대 쿼리를 method명으로 설정하지 않는다)
 * @author user
 */
public class UseStatementDAOSrvice {

	
	public static final String ADD="1";
	public static final String MODIFY="2";
	public static final String REMOVE="3";
	public static final String SEARCH_ONE="4";
	public static final String SEARCH_ALL="5";
	public static final String EXIT="6";
	
	private StatementDAO stmtDAO;
	
	public UseStatementDAOSrvice() {
		
	}//UseStatementDAOSrvice
	
	public void addCpEmp4() {
		System.out.println("사원추가");
	}//addCpEmp4
	
	public void modifyCpEmp4() {
		System.out.println("사원변경");
	}//moidfyCpEmp4
	
	public void removeCpEmp4() {
		System.out.println("사원삭제");
	}//removeCpEmp4
	
	public void searchOneCpEmp4() {
		System.out.println("사원조회");
	}//searchOneCpEmp4
	
	public void searchAllCpEmp4() {
		System.out.println("모든사원조회");	
	}//searchAllCpEmp4
	
	public void menu() {
		
		boolean exitFlag=false;
		String inputMenu="";
		
		
		do {
			inputMenu=JOptionPane.showInputDialog("메뉴선택\n1.사원추가 2.사원변경 3. 사원삭제 4.사원조회 5. 모든사원조회"
					+ "\n6. 모든 번호를 입력하세요");
			
			switch(inputMenu) {
				case ADD : addCpEmp4(); break;
				case MODIFY : modifyCpEmp4(); break;
				case REMOVE : removeCpEmp4(); break;
				case SEARCH_ONE : searchOneCpEmp4(); break;
				case SEARCH_ALL : searchAllCpEmp4(); break;
				case EXIT: exitFlag=true; break;
			}//end switch
			
		}while(!exitFlag); 
		
		
	}//menu
	
	
	
	public static void main(String[] args) {
		UseStatementDAOService usDS= new UseStatementDAOService();
		usDS.menu();
	}

}
