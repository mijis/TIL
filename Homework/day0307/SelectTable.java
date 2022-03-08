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

@SuppressWarnings("serial")
public class SelectTable extends JFrame implements ItemListener {

	private JLabel jlbl;
	private JComboBox<String> jcb;
	private JTextArea jta;
	private JScrollPane jspJtaOutput;
	private String tableName;
	
	public SelectTable() {
		super("���̺� ��ȸ");
		
//		SelectTableDAO stDAO = SelectTableDAO.getinstance();
//		try {
//			for(int i=0; i<stDAO.selectAllTab().size(); i++) {
//				jcb.addItem(stDAO.selectAllTab().get(i));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		jlbl=new JLabel("���̺�");
		jcb = new JComboBox<String>();
		
		
		JPanel jpanel=new JPanel();
		jpanel.setBorder(new TitledBorder("���̺� ����"));
		
		jpanel.add(jlbl);
		jpanel.add(jcb);
		
		jta = new JTextArea();
		jspJtaOutput = new JScrollPane(jta);
		jspJtaOutput.setBorder(new TitledBorder("��ȸ���"));
		
		jcb.addItemListener(this);
		
		add("North",jpanel);
		add("Center",jspJtaOutput);
		
		setBounds(100,100,500,600);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SelectTable
	
	
	//0308 �ϳ��� method�� �и��ؼ� �������
	private void searchTableData() {
		SelectTableDAO stDAO = SelectTableDAO.getinstance();
		try {
			
			//0308 ���� ������ for ����� ����
			List<String> tableList = stDAO.selectAllTab(); //���̺��� ��ȸ�Ͽ�
			
			for(String tname:tableList) {
				//0308 ���� ������ for ����� ����
//			for(int i=0; i<stDAO.selectAllTab().size(); i++) {
				jcb.addItem(tname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchTable(String tableName) {
		
		SelectTableDAO stDAO = SelectTableDAO.getinstance();
		
		try {
			tableName = jcb.getSelectedItem().toString();
			List<TableVO> list = stDAO.selectAllColumn(tableName);
			
			StringBuilder output = new StringBuilder();
			
			output.append("NUMBER\tCOLUMN_NAME\tDATA_TYPE\tDATA_LENGTH\n");
			
			//������ for�� �ξ� ����
			for(TableVO tVO :list) {
//			for(int i=0; i<list.size(); i++) {
//				tVO = list.get(i);
				output/*.append(i+1).append("\t")*/
				.append(tVO.getColumn_name()).append("\t").append("\t")
				.append(tVO.getData_type()).append("\t")
				.append(tVO.getData_length()).append("\n");
				
			}//end for
			
			if(list.isEmpty()) {
				jta.setText("���̺� ���� ���������ʽ��ϴ�.");
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
		new SelectTable().searchTableData();
	}//main
	
}//Work0307
