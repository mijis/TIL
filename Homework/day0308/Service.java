package day0308;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Service {
	
	public void searchMaker(String maker) {
		
		DAO d = DAO.getInstance();
		
		try {
			List<String> list = d.selectALL(maker);
			
			StringBuilder output =new StringBuilder();
			output.append("제조국\t제조사\t모델명\t연식\t가격\t옵션\n");
			
			for(String sb : list) {
				output.append(sb);
			}//end for
			
			JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(output.toString(),40,80)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//end searchMaker
	
	public static void main(String[] args) {
			String maker = JOptionPane.showInputDialog("차량 제조사");
			new Service().searchMaker(maker);
	}

}
