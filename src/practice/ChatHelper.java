package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

/**
 * ������ ������ �޾�, �޽����� ������ �б����� Stream�� �����ϰ� 
 * �޽����� ������ �Ͱ� ���ÿ� �����ϱ� ���ؼ� Thread�� �����Ͽ� �ڵ��Ѵ�.
 * 
 * @author user
 *
 */
public class ChatHelper extends Thread {

	private Socket someClient;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	
	private DefaultListModel<String> dlmServerMonitor;
//	private int count;
	private JScrollPane jspServerMonitor;
	
	String nick;
	
	public ChatHelper (Socket client, DefaultListModel<String> dlmServerMonitor, int cnt,
			JScrollPane jspServerMonito) throws IOException {
		
		//������ ������ �ް�
		this.someClient=client;
		this.dlmServerMonitor=dlmServerMonitor;
//		this.count=cnt;
		this.jspServerMonitor=jspServerMonito;
		//��Ʈ���� �����Ͽ� ��ȭ�� �о� ���� �� �ִ� ���¸� �����.
		disReadStream=new DataInputStream(someClient.getInputStream());
		dosWriteStream=new DataOutputStream(someClient.getOutputStream());
		
		nick = disReadStream.readUTF();
		//������ ����Ϳ� �����ڰ� �������� �����ش�.
		dlmServerMonitor.addElement(nick+"���� �����Ͽ����ϴ�.");
		dlmServerMonitor.addElement(nick+"�� ���� ���� ["+someClient.getInetAddress()+"]");
		setScrollBar();
		
		//��� �����ڿ��� ���� �����ڰ� �������� �˷��ش�.
		broadcast(nick+"���� �����Ͽ����ϴ�.");
	}
	
	private void setScrollBar() {
		jspServerMonitor.getVerticalScrollBar().setValue(
				jspServerMonitor.getVerticalScrollBar().getMaximum());
	}
	
	/**
	 * �޽����� ���ѷ����� �о�鿩, ��� �����ڿ��� �ѷ��ش�.
	 */
	@Override
	public void run() {//ChatSever�� run() �ȿ��� start() ȣ��
		
		String revMsg="";
			try {
				while(true) {
					revMsg=disReadStream.readUTF();//�޽����� �о�鿩
					broadcast(revMsg); //���ܸ� ����ϴ� �ڵ尡 while�ȿ� �־ ��� ���ܸ� ����߾��
				}//end while
			} catch (IOException ie) {
				//�޽����� �о������ ���ϴ� ���´� �����ڰ� ������ ������ ����
				//�����ڸ� �����ϴ� ����Ʈ���� ���� ��ü(helper)�� �����ϰ�,
				ChatServer.connectList.remove(this);
				//���� â�� �����ڰ� �������� �˷��ְ�
				dlmServerMonitor.addElement(nick+"���� ����Ͽ����ϴ�.");
				setScrollBar();
					//��� �����ڿ��� �������� �˷��ش�.
					try {
						broadcast(nick+"���� ����Ͽ����ϴ�.");
					} catch (IOException e) {
						e.printStackTrace();
					}//end catch
					
				ie.printStackTrace();
			}//end catch
	}//run
	
	
	/**
	 * �Է¹��� �޽����� ��� �����ڿ��� ������ ��
	 * @param msg �����ڵ鿡�� ���� �޽���
	 * @throws IOException
	 */
	public synchronized void broadcast(String msg) throws IOException{
		
		for(ChatHelper ch : ChatServer.connectList) {
			ch.dosWriteStream.writeUTF(msg);
		}
	}
	
}//ChatHelper
