package day0307;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * @author user
 *
 */
@SuppressWarnings("serial")
public class SelectTable extends JFrame implements ItemListener {

	private JLabel jlbl;
	private JComboBox<String> jcb;
	private JTextArea jta;
	private JScrollPane jspJtaOutput;
	private String tableName;
	
	public SelectTable() {
		super("테이블 조회");
		
		SelectTableDAO stDAO = SelectTableDAO.getinstance();
		
		jlbl=new JLabel("테이블");
		
		jcb = new JComboBox<String>();
		
		try {
			for(int i=0; i<stDAO.comboList().size(); i++) {
				jcb.addItem(stDAO.comboList().get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		JPanel jpanel=new JPanel();
		jpanel.setBorder(new TitledBorder("테이블 선택"));
		
		jpanel.add(jlbl);
		jpanel.add(jcb);
		
		jta = new JTextArea();
		jspJtaOutput = new JScrollPane(jta);
		jspJtaOutput.setBorder(new TitledBorder("조회결과"));
		
		jcb.addItemListener(this);
		
		add("North",jpanel);
		add("Center",jspJtaOutput);
		
		setBounds(100,100,500,600);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SelectTable
	
	public void searchTable(String table) {
		
		SelectTableDAO stDAO = SelectTableDAO.getinstance();
		
		try {
			String tableName = jcb.getSelectedItem().toString();
			List<TableVO> list = stDAO.selectTable(tableName);
			
			StringBuilder output = new StringBuilder();
			
			output.append("NUMBER\tCOLUMN_NAME\tDATA_TYPE\tDATA_LENGTH\n");
			
			TableVO tDAO = null;
			for(int i=0; i<list.size(); i++) {
				tDAO = list.get(i);
				output.append(i+1).append("\t")
				.append(tDAO.getColumn_name()).append("\t").append("\t")
				.append(tDAO.getData_type()).append("\t")
				.append(tDAO.getData_length()).append("\n");
				
			}//end for
			
			if(list.isEmpty()) {
				jta.setText("테이블에 값이 존재하지않습니다.");
			}
			
			jta.setText(output.toString());
			System.out.println("ss");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//select

	@Override
	public void itemStateChanged(ItemEvent ie) {
		tableName = jcb.getSelectedItem().toString();
//		if(jcb.getSelectedItem()) {
			searchTable(tableName);
//		}
	}

	
	
	public static void main(String[] args) {
		new SelectTable();
	}//main
	
}//Work0307
