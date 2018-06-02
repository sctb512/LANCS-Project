package com.PacketLANCS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Set;

//���հ�
public class TCPThread extends Thread {
	Socket msgSocket;
	public ObjectOutputStream sendStream;
	public ObjectInputStream acceptStream;
	
	

	public Socket getMsgSocket() {
		return msgSocket;
	}

	public void setMsgSocket(Socket msgSocket) {
		this.msgSocket = msgSocket;
	}

	public TCPThread(int userID) {
		try {
			msgSocket = new Socket("119.29.221.132", 1212);
			//�����
			sendStream = new ObjectOutputStream(new BufferedOutputStream(msgSocket.getOutputStream()));
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ChatMessage connectifo = new ChatMessage("connecttest");
		connectifo.sourceUid=userID;
		try {
			sendStream.writeObject(connectifo);
			sendStream.flush();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	// DatagramSocket dgsocket;
	// InetAddress address;
	// public void run(){
	// try {
	// try {
	// address = InetAddress.getByName("119.29.221.132");
	// } catch (UnknownHostException e) {
	// e.printStackTrace();
	// }
	// dgsocket=new DatagramSocket(1212,address);
	// } catch (SocketException e) {
	// e.printStackTrace();
	// }
	// }

	public void run() {
		try {
			acceptStream = new ObjectInputStream(new BufferedInputStream(msgSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChatMessage resultmsg = null;

		try {
			//���ܷ�������Ϣ��һֱ����
			while ((resultmsg = (ChatMessage) acceptStream.readObject()) != null) {
				System.out.println("receive : " + resultmsg.flag);
				System.out.println(resultmsg.messageContent);
				System.out.println("receive nickname :" + resultmsg.nickname);
				if (resultmsg.targetUid == 1111111111) {
					GroupChat.Messageupdata(resultmsg.nickname, resultmsg.messageContent);
				} else {
					//
					
					Set<Integer> key = ToOneChatRoom.friendlistmap.keySet();
					System.out.println("����"+key.size());
					for (Integer i : key) {

						System.out.println("������" + i);
						System.out.println("read target uid in set :" + i.intValue());
						if (i.intValue() == resultmsg.sourceUid) {
							System.out.println("write frame found !");
							System.out.println("it's :" + i.intValue());
							ToOneChatRoom targetDemo = ToOneChatRoom.friendlistmap.get(i);

							targetDemo.Messageupdata(resultmsg.nickname, resultmsg.messageContent);
							break;
						}
						
					}


					

//					ToOneChatRoom targetDemo = ToOneChatRoom.friendlistmap.get(resultmsg.targetUid);
//					targetDemo.Messageupdata(resultmsg.nickname, resultmsg.messageContent);
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  void sendmsg(ChatMessage msg) {
		try {
			sendStream.writeObject(msg);
			sendStream.flush();
			System.out.println("send succeed !");
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
//	public static  void sendmsgstatic(ChatMessage msg) {
//		try {
//			sendStream.writeObject(msg);
//			sendStream.flush();
//		} catch (IOException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
//	}
}
