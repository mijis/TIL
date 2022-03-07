package kr.co.sist.statement.service;

import java.awt.Menu;

import javax.swing.JOptionPane;

import kr.co.sist.statement.dao.StatementDAO;

/**
 * ���������� ó���ϸ鼭 �ʿ信 ���� DB�۾��� ����ϴ� Ŭ����<br>
 * XXXService Ŭ������ method���� ������ ���� �ۼ��Ѵ� (���� ������ method������ �������� �ʴ´�)
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
		System.out.println("����߰�");
	}//addCpEmp4
	
	public void modifyCpEmp4() {
		System.out.println("�������");
	}//moidfyCpEmp4
	
	public void removeCpEmp4() {
		System.out.println("�������");
	}//removeCpEmp4
	
	public void searchOneCpEmp4() {
		System.out.println("�����ȸ");
	}//searchOneCpEmp4
	
	public void searchAllCpEmp4() {
		System.out.println("�������ȸ");	
	}//searchAllCpEmp4
	
	public void menu() {
		
		boolean exitFlag=false;
		String inputMenu="";
		
		
		do {
			inputMenu=JOptionPane.showInputDialog("�޴�����\n1.����߰� 2.������� 3. ������� 4.�����ȸ 5. �������ȸ"
					+ "\n6. ��� ��ȣ�� �Է��ϼ���");
			
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
