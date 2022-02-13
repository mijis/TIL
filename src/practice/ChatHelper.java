package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

/**
 * 접속자 소켓을 받아, 메시지를 보내고 읽기위한 Stream을 연결하고 
 * 메시지를 보내는 것과 동시에 실행하기 위해서 Thread를 도입하여 코딩한다.
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
		
		//접속자 소켓을 받고
		this.someClient=client;
		this.dlmServerMonitor=dlmServerMonitor;
//		this.count=cnt;
		this.jspServerMonitor=jspServerMonito;
		//스트림을 연결하여 대화를 읽어 들일 수 있는 상태를 만든다.
		disReadStream=new DataInputStream(someClient.getInputStream());
		dosWriteStream=new DataOutputStream(someClient.getOutputStream());
		
		nick = disReadStream.readUTF();
		//관리자 모니터에 접속자가 들어왔음을 보여준다.
		dlmServerMonitor.addElement(nick+"님이 접속하였습니다.");
		dlmServerMonitor.addElement(nick+"님 접속 정보 ["+someClient.getInetAddress()+"]");
		setScrollBar();
		
		//모든 접속자에게 현재 접속자가 들어왔음을 알려준다.
		broadcast(nick+"님이 접속하였습니다.");
	}
	
	private void setScrollBar() {
		jspServerMonitor.getVerticalScrollBar().setValue(
				jspServerMonitor.getVerticalScrollBar().getMaximum());
	}
	
	/**
	 * 메시지를 무한루프로 읽어들여, 모든 접속자에게 뿌려준다.
	 */
	@Override
	public void run() {//ChatSever의 run() 안에서 start() 호출
		
		String revMsg="";
			try {
				while(true) {
					revMsg=disReadStream.readUTF();//메시지를 읽어들여
					broadcast(revMsg); //예외를 출력하는 코드가 while안에 있어서 계속 예외를 출력했어요
				}//end while
			} catch (IOException ie) {
				//메시지를 읽어들이지 못하는 상태는 접속자가 접속을 종료한 상태
				//접속자를 관리하는 리스트에서 현재 객체(helper)를 삭제하고,
				ChatServer.connectList.remove(this);
				//서버 창에 접속자가 나갔음을 알려주고
				dlmServerMonitor.addElement(nick+"님이 퇴실하였습니다.");
				setScrollBar();
					//모든 접속자에게 나갔음을 알려준다.
					try {
						broadcast(nick+"님이 퇴실하였습니다.");
					} catch (IOException e) {
						e.printStackTrace();
					}//end catch
					
				ie.printStackTrace();
			}//end catch
	}//run
	
	
	/**
	 * 입력받은 메시지를 모든 접속자에게 보내는 일
	 * @param msg 접속자들에게 보낼 메시지
	 * @throws IOException
	 */
	public synchronized void broadcast(String msg) throws IOException{
		
		for(ChatHelper ch : ChatServer.connectList) {
			ch.dosWriteStream.writeUTF(msg);
		}
	}
	
}//ChatHelper
