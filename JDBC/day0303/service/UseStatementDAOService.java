package kr.co.sist.statement.service;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.statement.dao.StatementDAO;
import kr.co.sist.statement.vo.CpEmp4InsertVO;
import kr.co.sist.statement.vo.CpEmp4UpdateVO;

/**
 * ���������� ó���ϸ鼭 �ʿ信 ���� DB�۾��� ����ϴ� Ŭ����<br>
 * XXXService Ŭ������ method���� ������ ���� �ۼ��Ѵ� (���� ������ method������ �������� �ʴ´�)
 * @author user
 */
public class UseStatementDAOService {

	
	public static final String ADD="1";
	public static final String MODIFY="2";
	public static final String REMOVE="3";
	public static final String SEARCH_ONE="4";
	public static final String SEARCH_ALL="5";
	public static final String EXIT="6";
	
	private StatementDAO stmtDAO;
	
	public UseStatementDAOService() {
		stmtDAO = new StatementDAO();
	}//UseStatementDAOSrvice
	
	public void addCpEmp4() {
		String tempData=JOptionPane.showInputDialog
				("�߰��� ��������� \",\"�� �����Է�\n�����ȣ,�����,����,����,���ʽ�");
		String[] data=tempData.split(",");
		
		if(data.length!=5) { //���������� �Է»�Ȳ
			JOptionPane.showMessageDialog(null, "�Էµ������� ������ �����ʽ��ϴ�");
			return; // early return;
		}//end if
		
		//�� if�� ���ǿ� ���� ���� �� ��Ȳ�ڵ� (else�� ���� �ڵ�)
		// �������� �Է� ��Ȳ
		//1. ���� ���� ���� ���ڿ� => ��ȯ
		try {
			//���ҵ� ���� 
			int empno=Integer.parseInt(data[0]);
			String ename=data[1];
			String job=data[2];
			int sal=Integer.parseInt(data[3]);
			double comm=Double.parseDouble(data[4]);
			
			//2. VO ���� �־� (�ϳ��� ��ü�� ��Ƽ� ó��)
			CpEmp4InsertVO ceiVO=new CpEmp4InsertVO(empno, sal, ename, job, comm);
			
			//3. DAO�� ����
			stmtDAO.insertCpEmp4(ceiVO);
			JOptionPane.showMessageDialog(null, ceiVO.getEmpno()+"�� ��������� �߰��Ͽ����ϴ�.");
			
		}catch(SQLException se) {
			JOptionPane.showMessageDialog(null, "����߰��۾��� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
			//�پ��� ���ܻ�Ȳ ó��
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			switch(errCode) {
				case 1: //ORA-00001
					sqlErrMsg= "���� ��� ��ȣ�� �̹� �����մϴ�."; break;
				case 925 : //ORA-00925
					sqlErrMsg= "�������� �߸��Ǿ����ϴ�."; break;
				case 1438 : //ORA-01438
					sqlErrMsg= "�����ȣ�� ���� 4�ڸ�����, ������ 5�ڸ�, ���ʽ� 5�ڸ��Դϴ�."; break;
				case 12899 : //ORA-12899
					sqlErrMsg= "������� ���� 10�� �ѱ� 3�� �̳��̾���ϰ�, ������ ���� 9�� �ѱ� 3�� �̳��̾��մϴ�."; break;
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
			
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "�����ȣ, ����, ���ʽ��� �����̾���մϴ�.");
		}//end catch
		
	}//addCpEmp4
	
	public void modifyCpEmp4() {
		String tempData=JOptionPane.showInputDialog
				("������ ��������� \",\"�� �����Է�\n�����ȣ,����,����,���ʽ�\n�����ȣ�� ��ġ�ϴ� ����� ����,����,���ʽ��� �����մϴ�");
		String[] data=tempData.split(",");
		
		if(data.length!=4) { //���������� �Է»�Ȳ
			JOptionPane.showMessageDialog(null, "�Էµ������� ������ �����ʽ��ϴ�. �Է°��� 4���̾���մϴ�.");
			return; // early return;
		}//end if
		
		//�������� �Է»�Ȳ
		//1. �������� ����
		try {
			int empno=Integer.parseInt(data[0]);
			String job=data[1];
			int sal=Integer.parseInt(data[2]);
			double comm=Double.parseDouble(data[3]);
			
			//2. VO �� ����
			CpEmp4UpdateVO ceuVO=new CpEmp4UpdateVO(empno, job, sal, comm);
			
			//3. DB ����
			int cnt=stmtDAO.updateCpEmp4(ceuVO);
			
			String msg=ceuVO.getEmpno()+"�� ����� �������� �ʽ��ϴ�.";
			
			if(cnt==1) {
				msg=ceuVO.getEmpno()+"�� ��������� ����Ǿ����ϴ�.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
		}catch(SQLException se) {
			se.printStackTrace();
			//�پ��� ���ܻ�Ȳ ó��
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			switch(errCode) {
				case 925 : //ORA-00925
					sqlErrMsg= "�������� �߸��Ǿ����ϴ�."; break;
				case 1438 : //ORA-01438
					sqlErrMsg= "������ 5�ڸ�, ���ʽ� 5�ڸ��Դϴ�."; break;
				case 12899 : //ORA-12899
					sqlErrMsg= "������ ���� 9�� �ѱ� 3�� �̳��̾��մϴ�."; break;
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "�����ȣ, ����, ���ʽ��� �����̾���մϴ�.");
		}//end catch
		
		
	}//moidfyCpEmp4
	
	public void removeCpEmp4() {
		String tempData=JOptionPane.showInputDialog("������ ��������� �Է����ּ���.");
		
		try{
			//�Է°� ó��
			int empno=Integer.parseInt(tempData);
			//DB �۾� ����
			int cnt=stmtDAO.deleteCpEmp4(empno);
			
			String msg=empno+"�� ����� �������� �ʽ��ϴ�.";
			
			if(cnt==1) {
				msg=empno+"�� ��������� �����Ǿ����ϴ�.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
		}catch(SQLException se) {
			se.printStackTrace();
			
			//�پ��� ���ܻ�Ȳ ó��
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			switch(errCode) {
				case 925 : //ORA-00925
					sqlErrMsg= "�������� �߸��Ǿ����ϴ�."; break;
		
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
			
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "�����ȣ�� ���ڷθ� �����˴ϴ�.");
		}
		
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
		
		try {
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
		}catch(NullPointerException npe) {
			//����ڰ� ��ҳ�, x�� ���� ���
		}finally{
			JOptionPane.showMessageDialog(null, "���α׷��� ����Ǿ����ϴ�");
		}
		
	}//menu
	
	
	
	public static void main(String[] args) {
		UseStatementDAOService usDAOs= new UseStatementDAOService();
		usDAOs.menu();
	}

}
