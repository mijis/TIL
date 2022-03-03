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
				JOptionPane.showMessageDialog(null, name+ "을 추가했습니다.");
			
			}catch (SQLException se){
				JOptionPane.showMessageDialog(null, "이름추가작업에 문제가 발생하였습니다.");
				se.printStackTrace();
				
				//다양한 예외상황 처리
				int errCode=se.getErrorCode();
				String sqlErrMsg="";
				switch(errCode) {
					case 1: //ORA-00001
						sqlErrMsg= "같은 이름이 이미 존재합니다."; break;
					case 925 : //ORA-00925
						sqlErrMsg= "쿼리문이 잘못되었습니다."; break;
					case 12899 : //ORA-12899
						sqlErrMsg= "이름은은 영어 10자 한글 3자 이내이어야합니다."; break;
				}//end switch
				JOptionPane.showMessageDialog(null, sqlErrMsg);
			}
		}
		d.dispose();
	}
}//actionlistener

