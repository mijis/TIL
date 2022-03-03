package day0303_work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Event_Service extends WindowAdapter implements ActionListener{
		
	private Design d;
	
	public Event_Service(Design d) {
		this.d=d;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==d.getJbtnAdd()) {

			try {
				String name = d.getJtfName().getText();
				
				DAO d = new DAO();
				InsertVO iVO = new InsertVO(name);
				
				d.insertName(iVO);
				JOptionPane.showMessageDialog(null, name+ "�� �߰��߽��ϴ�.");
			
			}catch (SQLException se){
				JOptionPane.showMessageDialog(null, "�̸��߰��۾��� ������ �߻��Ͽ����ϴ�.");
				se.printStackTrace();
				
				//�پ��� ���ܻ�Ȳ ó��
				int errCode=se.getErrorCode();
				String sqlErrMsg="";
				switch(errCode) {
					case 1: //ORA-00001
						sqlErrMsg= "���� �̸��� �̹� �����մϴ�."; break;
					case 925 : //ORA-00925
						sqlErrMsg= "�������� �߸��Ǿ����ϴ�."; break;
					case 12899 : //ORA-12899
						sqlErrMsg= "�̸����� ���� 10�� �ѱ� 3�� �̳��̾���մϴ�."; break;
				}//end switch
				JOptionPane.showMessageDialog(null, sqlErrMsg);
			}
		}
		d.dispose();
	}
}//actionlistener

