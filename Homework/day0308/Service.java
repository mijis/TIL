package day0308;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Service {
	DAO d = DAO.getInstance();
	
	//�����ڿ��� searchCar ������ ��ȯ
	public Service() throws SQLException {
		String maker = JOptionPane.showInputDialog("���� ������");
		
		//if(maker ==null)�� �� �ȵǴ��� �ذ������� ���Ͽ����� switch ����Ͽ� null���� ����
		switch(maker) {
		case "":	JOptionPane.showMessageDialog(null, "�����縦 �Է��ϼ���"); return;
		default :	searchCar(maker); break;
		}
		
		
	}//Service
	
	
	public void searchCar(String maker) {
		
		
		try {
			List<String> list = d.selectALL(maker);
			
			StringBuilder output =new StringBuilder();
			output.append("������\t������\t�𵨸�\t����\t����\t�ɼ�\n");
			
			for(String sb : list) {
				output.append(sb);
			}//end for
			
			//early return�� ����Ͽ� ��ġ�ϴ� ����� ���� �� �߰�
			if(!d.selectMaker().contains(maker)) {
				JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea("��ġ�ϴ� �����簡 �����ϴ�.",40,80)));
				return;
			};
			
			JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(output.toString(),40,80)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//end searchMaker
	
	public static void main(String[] args) {
			
			try {
				new Service();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
