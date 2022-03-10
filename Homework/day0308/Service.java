package day0308;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Service {
	DAO d = DAO.getInstance();
	
	//생성자에서 searchCar 쓰도록 변환
	public Service() throws SQLException {
		String maker = JOptionPane.showInputDialog("차량 제조사");
		
		//if(maker ==null)이 왜 안되는지 해결하지는 못하였으나 switch 사용하여 null값을 잡음
		switch(maker) {
		case "":	JOptionPane.showMessageDialog(null, "제조사를 입력하세요"); return;
		default :	searchCar(maker); break;
		}
		
		
	}//Service
	
	
	public void searchCar(String maker) {
		
		
		try {
			List<String> list = d.selectALL(maker);
			
			StringBuilder output =new StringBuilder();
			output.append("제조국\t제조사\t모델명\t연식\t가격\t옵션\n");
			
			for(String sb : list) {
				output.append(sb);
			}//end for
			
			//early return을 사용하여 일치하는 결과가 없을 때 추가
			if(!d.selectMaker().contains(maker)) {
				JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea("일치하는 제조사가 없습니다.",40,80)));
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
