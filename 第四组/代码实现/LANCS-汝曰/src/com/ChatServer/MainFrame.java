/*
 * 通信协议： 
 *		注册：reg
 *		登陆：log
 *		服务器返回值：result
 *		返回值：
 *			登陆：	   0：密码错误	1：账号不存在
 *		请求类型：rq
 *		用户信息：ownIfo  
 *		好友信息：frdIfo
 *
 *				
 */

package com.ChatServer;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.PacketLANCS.FriendsIfo;
import com.PacketLANCS.Message;

public class MainFrame {

	public static void main(String[] args) throws Exception {
		new serverFrame().launch();

	}

}

@SuppressWarnings("serial")
class serverFrame extends JFrame {
	public void launch() throws Exception {

		// 测试用

		// 主Frame
		JFrame mainFrame = new JFrame("汝曰在线管理系统");
		mainFrame.setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setSize(600, 450);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel logo = new JLabel("logo");
		logo.setBackground(Color.BLACK);
		logo.setLayout(null);
		logo.setBounds(0, 0, 100, 30);

		Button start = new Button("开启服务");
		start.setBounds(300, 30, 60, 20);

		JLabel ifo = new JLabel("服务器状态");
		ifo.setBounds(0, 35, 600, 130);

		TextField userName = new TextField();
		userName.setBounds(160, 200, 100, 30);

		TextField passWord = new TextField();
		passWord.setBounds(270, 200, 100, 30);

		JButton addIfo = new JButton("添加");
		addIfo.setBounds(380, 200, 80, 30);

		JPanel userTable = new JPanel();
		userTable.setBounds(0, 230, 600, 200);
		userTable.setBackground(Color.green);

		mainFrame.add(logo);
		mainFrame.add(start);
		mainFrame.add(ifo);
		mainFrame.add(userName);
		mainFrame.add(passWord);
		mainFrame.add(addIfo);
		mainFrame.add(userTable);

		serverThread ser = new serverThread();

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ser.start();
				new TCPMessqgeThread().start();
				ifo.setText("服务器状态：TCP服务器运行中...");
				

			}
		});

		mainFrame.setVisible(true);
	}
}

class serverThread extends Thread {

	@SuppressWarnings("resource")
	public void run() {
		Socket wait = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1200);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				wait = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (wait != null) {
				System.out.println("new client ! ");
				new acceptThread(wait).start();
			}
		}

	}
}

class acceptThread extends Thread {
	Socket wait;

	ObjectInputStream acceptStream;
	ObjectOutputStream sendStream;

	Message acceptMsg;

	static int NUMBER = 100000000;
	static Map<Integer, Socket> clientSocket;

	boolean haveNewOnlineUser = false;
	static boolean haveNewMessage = false;
	static String[] messageSet = null;

	public acceptThread(Socket wait) {
		this.wait = wait;
		try {
			sendStream = new ObjectOutputStream(new BufferedOutputStream(wait.getOutputStream()));

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			acceptStream = new ObjectInputStream(new BufferedInputStream(wait.getInputStream()));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		Connection conDbc = JDBCTools.getConnection();
		try {
			Statement stat = conDbc.createStatement();
			String sql = "select max(Uid) Uido from userlist;";

			ResultSet rs = stat.executeQuery(sql);
			if(rs!=null){
				if(rs.next())
				{
					acceptThread.NUMBER=rs.getInt("Uido");
				}
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		System.out.println("now number !"+NUMBER);
	}

	public void run() {
		try {
			acceptMsg = (Message) acceptStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (acceptMsg.flag.equals("reg")) {
			System.out.println("ok!");
			NUMBER++;

			Connection conDbc = JDBCTools.getConnection();
			try {
				Statement stat = conDbc.createStatement();
				String sql = "insert into userlist(Uid,UserName,PassWord,NickName,Sign,QQ,IsOnline) values("+NUMBER+ ",'" + acceptMsg.NickName + "','" + acceptMsg.UserName + "','" + acceptMsg.PassWord + "','"
						+ acceptMsg.Sign + "'," + acceptMsg.QQ + ",0);";
				int rs = stat.executeUpdate(sql);
				Message Msg = new Message("regstate");
				if (rs > 0) {
					System.out.println("succeed");
					try {

						Msg.setIfo(1);
						;
						sendStream.writeObject(Msg);
						sendStream.flush();
						System.out.println("send succeed!" + Msg);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("faield");
					try {

						Msg.setIfo(0);
						;
						sendStream.writeObject(Msg);
						sendStream.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (acceptMsg.flag.equals("log")) {
			System.out.println("connect!");

			Connection conDbc = JDBCTools.getConnection();
			try {
				Statement stat = conDbc.createStatement();
				String sql = "select PassWord,Uid from userlist where UserName='" + acceptMsg.UserName + "';";
				ResultSet rs = stat.executeQuery(sql);

				Statement frstat = conDbc.createStatement();
				String frsql = "update userlist set IsOnline=1 where UserName='" + acceptMsg.UserName + "';";
				int fsrs = frstat.executeUpdate(frsql);
				Message Msg = new Message("logstate");

				if (rs.next()) {
					if (rs.getString("PassWord").equals(acceptMsg.PassWord) && fsrs > 0) {
						System.out.println("log succeed");
						haveNewOnlineUser = true;
						try {
							Msg.setIfo(1, rs.getInt("Uid"));
							System.out.println(rs.getInt("Uid") + "log succeed !");
							sendStream.writeObject(Msg);
							sendStream.flush();

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						System.out.println("wrong password");
						try {

							Msg.setIfo(0);
							;

							sendStream.writeObject(Msg);
							sendStream.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					System.out.println("faield");
					try {
						Msg.setIfo(2);

						sendStream.writeObject(Msg);
						sendStream.flush();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (acceptMsg.flag.equals("ownifo")) {

			Connection conDbc = JDBCTools.getConnection();
			try {
				System.out.println(acceptMsg.Uid);
				Statement stat = conDbc.createStatement();
				String sql = "select Uid,UserName,QQImg,NickName,Sign,QQ from userlist where Uid=" + acceptMsg.Uid + ";";
				ResultSet rs = stat.executeQuery(sql);

				if (rs.next()) {
					System.out.println("ownifo connect!");
					int img = rs.getInt("QQImg");
					String nickname = rs.getString("NickName");
					String sign = rs.getString("Sign");
					int uid = rs.getInt("Uid");
					String username = rs.getString("UserName");
					int qq = rs.getInt("QQ");

					System.out.println(img + nickname + sign);

					try {
						System.out.println(img + "  " + nickname + sign);

						Message Msg = new Message("owniforesult");
						Msg.setIfo(img, nickname, sign,uid,username,qq);

						sendStream.writeObject(Msg);
						sendStream.flush();

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (acceptMsg.flag.equals("friendlist")) {

			Connection conDbc = JDBCTools.getConnection();
			try {
				System.out.println("friendlist" + acceptMsg.Uid);
				Statement stat = conDbc.createStatement();
				String sql = "select FriendUid from userrelation where Uid=" + acceptMsg.Uid + ";";
				ResultSet rs = stat.executeQuery(sql);
				int i = 0;
				System.out.println("friendlist yes !");
				FriendsIfo[] friendlist = new FriendsIfo[100];
				while (rs.next()) {
					int friendUid = rs.getInt("FriendUid");
					System.out.println(friendUid);
					Statement resultstat = conDbc.createStatement();
					String resultsql = "select Uid,UserName,QQ,QQImg,NickName,Sign from userlist where Uid=" + friendUid
							+ ";";
					ResultSet result = resultstat.executeQuery(resultsql);
					if (result.next()) {
						System.out.println("yes!");
						int img = result.getInt("QQImg");
						String nickname = result.getString("NickName");
						String sign = result.getString("Sign");

						String username = result.getString("UserName");

						int QQ = result.getInt("QQ");
						int Uid = result.getInt("Uid");
						friendlist[i++] = new FriendsIfo(Uid, username, nickname, sign, img, QQ);

						System.out.println("friend 1 !" + friendlist[i - 1].img + friendlist[i - 1].NickName
								+ friendlist[i - 1].Sign);
					}
				}

				try {

					Message Msg = new Message("friendsresultifo");
					Msg.setIfo(friendlist);
					sendStream.writeObject(Msg);
					sendStream.flush();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (acceptMsg.flag.equals("grouplist")) {
			System.out.println("group success!");
			Connection conDbc = JDBCTools.getConnection();
			try {
				Statement stat = conDbc.createStatement();
				String sql = "select Uid,UserName,QQ,QQImg,NickName,Sign from userlist where IsOnline='1';";
				ResultSet rs = stat.executeQuery(sql);
				int i = 0;
				FriendsIfo[] grouplist = new FriendsIfo[100];
				while (rs.next()) {

					System.out.println("yes!");
					int img = rs.getInt("QQImg");
					String nickname = rs.getString("NickName");
					String sign = rs.getString("Sign");

					String username = rs.getString("UserName");

					int QQ = rs.getInt("QQ");
					int Uid = rs.getInt("Uid");

					grouplist[i++] = new FriendsIfo(Uid, username, nickname, sign, img, QQ);

					System.out.println(grouplist[i - 1].img + grouplist[i - 1].NickName);

				}

				try {

					Message Msg = new Message("groupresultifo");
					Msg.setIfo(grouplist);

					sendStream.writeObject(Msg);
					sendStream.flush();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (acceptMsg.flag.equals("groupmsg")) {
		}
		try {
			sendStream.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("msgHandle start !");
	}

	public void sendMessage(Message msg) {
		try {
			sendStream.writeObject(msg);
			sendStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMessage(Message msg, Socket targetSocket) {
		try {
			ObjectOutputStream sendStream = new ObjectOutputStream(
					new BufferedOutputStream(targetSocket.getOutputStream()));
			sendStream.writeObject(msg);
			sendStream.flush();
			targetSocket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
