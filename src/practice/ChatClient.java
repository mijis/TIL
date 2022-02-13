package practice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ChatClient extends JFrame implements ActionListener, Runnable {

	private JTextArea jtaTalkDisplay;
	private JTextField jtfServerIp, jtfNickName, jtfTalkInput;
	private JButton jbtnConnectServer, jbtnCapture;
	
	private JScrollPane jspJtaTalkDisplay;
	
	private Socket someclient;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	
	private Thread threadClient;
	
	private String nick;
	
	public ChatClient() {
		super("::::::::::::: ä�� Ŭ���̾�Ʈ :::::::::::::::::::");
		jtfServerIp=new JTextField("211.63.89.",8);
		jtfNickName=new JTextField(10);
		jtfTalkInput=new JTextField();
		
		jbtnConnectServer=new JButton("��������");
		jbtnCapture=new JButton("��ȭ����");
		
		jtaTalkDisplay=new JTextArea();
		jspJtaTalkDisplay=new JScrollPane( jtaTalkDisplay );
		
		jtaTalkDisplay.setEditable(false);
		
		JPanel jpNorth=new JPanel();
		jpNorth.add(new JLabel("�����ּ�"));
		jpNorth.add( jtfServerIp);
		jpNorth.add(new JLabel("��ȭ��"));
		jpNorth.add( jtfNickName);
		jpNorth.add( jbtnConnectServer);
		jpNorth.add( jbtnCapture);
		
		jpNorth.setBorder(new TitledBorder("��������"));
		jspJtaTalkDisplay.setBorder(new TitledBorder("��ȭ����"));
		jtfTalkInput.setBorder(new TitledBorder("��ȭ"));
		
		add("North", jpNorth );
		add("Center", jspJtaTalkDisplay );
		add("South",jtfTalkInput);

		jbtnCapture.addActionListener(this);
		jbtnConnectServer.addActionListener(this);
		jtfTalkInput.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}//windowClosing

			@Override
			public void windowClosed(WindowEvent we) {
				try {
					if(disReadStream!=null) {disReadStream.close();}
					if(dosWriteStream!=null) {dosWriteStream.close();}
					if(someclient!=null) {someclient.close();}
				}catch(IOException ie) {
					System.exit(JFrame.ABORT);
				}
			}//windowClosed
			
		});
		
		setBounds(100, 100, 700, 500);
		setVisible(true);
	}//ChatClient
	
	@Override
	public void run() {
		//�޽����� ���� ������ �о� ���δ�.(connectToServer()���� start()�� ȣ��)
		try {
			while(true) {
				jtaTalkDisplay.append(disReadStream.readUTF());
				jtaTalkDisplay.append("\n");
				//��ȭ�� ���뿡 ���� ��ũ�ѹٸ� ���� �Ʒ��� ������.
				jspJtaTalkDisplay.getVerticalScrollBar().setValue(
						jspJtaTalkDisplay.getVerticalScrollBar().getMaximum());
			}
		} catch (IOException ie) {
			JOptionPane.showMessageDialog(this, "������ ����Ǿ����ϴ�.");
			ie.printStackTrace();
		}
	}//run

	private void talkCapture() throws IOException {
		File directory=new File("e:/javachat/capture");
		if(!directory.exists()) {
			directory.mkdirs();
		}
		File file =new File(directory.getAbsoluteFile()+"/cap_"+System.currentTimeMillis()+".dat");
		System.out.println(file);
		
		BufferedWriter bw = null;
		try {
			//ĸó ������ �����ϱ� ���� ��Ʈ���� ����
			bw=new BufferedWriter(new FileWriter(file));
			bw.write(jtaTalkDisplay.getText());
			bw.flush();
			JOptionPane.showMessageDialog(this, file.getName()+"�� ��ȭ������ ����Ǿ����ϴ�.");
		}finally {
			if(bw!=null) {bw.close();}
		}
		
	}
	
	private void connectToSever() throws UnknownHostException, IOException {
		if(someclient!=null && someclient.isConnected()) {
			JOptionPane.showMessageDialog(this, "������ ���ӵǾ��ֽ��ϴ�");
			return;
		}
		
		//������ �����Ͽ� ������ �����ϰ�
		String severIpAddress = jtfServerIp.getText().replaceAll(" ","");
		someclient=new Socket(severIpAddress, 25000);
		
		
		//��ũ���� �����Ͽ� �����͸� �аų� ���� �� �ֵ��� ����� 
		disReadStream = new DataInputStream(someclient.getInputStream());
		dosWriteStream = new DataOutputStream(someclient.getOutputStream());
		
		//�����͸� �о���� �� �ִ� ����
		threadClient =new Thread(this);
		threadClient.start(); //-> run()
		
		jtaTalkDisplay.setText("������ �����Ͽ����ϴ�.\n��ſ� ä��\n");
		//Ŀ���� ��ȭ�Է�â�� �ִ´�.
		jtfTalkInput.requestFocus(); 
	}
	
	private void sendMsg() throws IOException{
		String talk=jtfTalkInput.getText();
		nick=jtfNickName.getText();
		dosWriteStream.writeUTF("["+nick+"] "+talk);
		jtfTalkInput.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) { //��ȭ���� ����
		if(ae.getSource() ==jbtnCapture) {
			try {
				talkCapture();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(ae.getSource() ==jbtnConnectServer) {//��������
			try {
				connectToSever();
				nick=jtfNickName.getText();
				dosWriteStream.writeUTF(nick);
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(this,"������ �������� �ʽ��ϴ�.");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(ae.getSource() ==jtfTalkInput) {//��ȭ�Է�
			try {
				sendMsg();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this,"������ �������� �ʽ��ϴ�.");
				e.printStackTrace();
			}
		}
	}//actionPerformed


	public static void main(String[] args) {
		new ChatClient();
		
		
	}//main

}//class
