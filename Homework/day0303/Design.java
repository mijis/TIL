package day0303_work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Design extends JFrame {

	private JTextField jtfName;
	private JButton jbtnAdd;
	
	
	public Design() {
		super("ÀÌ¸§ Ãß°¡");
		JLabel jlblbName = new JLabel("ÀÌ¸§");
		jtfName = new JTextField(10);
		jbtnAdd = new JButton("ÀÔ·Â");
		JTextArea jtaNameDisp = new JTextArea();
		
		JPanel jpNorth = new JPanel();
		
		jpNorth.add(jlblbName);
		jpNorth.add(jtfName);
		jpNorth.add(jbtnAdd);
		
		add("North", jpNorth);
		add("Center", jtaNameDisp);
		
		setSize(400,100);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		Event_Service es = new Event_Service(this);
		addWindowListener(es);
		jbtnAdd.addActionListener(es);
	}
	
	
	public JTextField getJtfName() {
		return jtfName;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public static void main(String[] args) {
		new Design();
	}

}
