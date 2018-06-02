package com.ChatServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.PacketLANCS.ChatMessage;

public class TCPMessqgeThread extends Thread {
	// DatagramSocket dgsocket;

	@SuppressWarnings("resource")
	public void run() {
		Socket wait = null;
		ServerSocket chatSocket = null;
		try {
			chatSocket = new ServerSocket(1212);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				wait = chatSocket.accept();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (wait != null) {
				System.out.println("new client ! ");
				new TcpMessageThread(wait).start();
			}
		}

	}
}

class TcpMessageThread extends Thread {
	Socket wait;

	public static Map<Integer, ObjectOutputStream> socketlist = new HashMap<Integer, ObjectOutputStream>();
	ObjectInputStream acceptStream;
	ObjectOutputStream sendStream;

	ChatMessage acceptMsg;

	public TcpMessageThread(Socket wait) {
		this.wait = wait;
	}

	public void run() {
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
		try {
			Set<Integer> key = socketlist.keySet();
			System.out.println("个数"+key.size());
			while ((acceptMsg = (ChatMessage) acceptStream.readObject()) != null) {
				
				// ID key
				if (!socketlist.containsKey(acceptMsg.sourceUid)) // 不存在再保存
					socketlist.put(new Integer(acceptMsg.sourceUid), sendStream);
				Set<Integer> key1 = socketlist.keySet();
				System.out.println("个数"+key1.size());
				if (acceptMsg.targetUid == 1111111111) {
					sendEveryOne(acceptMsg);
				} else {
					// 目标ID
					sendOne(acceptMsg.targetUid, acceptMsg);
				}

			}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void sendEveryOne(ChatMessage msg) {
		Iterator<Map.Entry<Integer, ObjectOutputStream>> list = socketlist.entrySet().iterator();
		while (list.hasNext()) {

			Map.Entry<Integer, ObjectOutputStream> entry = list.next();
			ObjectOutputStream sendstream = (ObjectOutputStream) entry.getValue();
			try {
				System.out.println(sendstream);
				sendstream.writeObject(msg);
				sendstream.flush();
				System.out.println("receive nickname :" + msg.nickname);
				System.out.println("chat message : " + msg.messageContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("send message to all ok!");

		}

	}

	public void sendOne(int target, ChatMessage msg) {

		// ObjectOutputStream sendstream =
		// (ObjectOutputStream)socketlist.get(target);
		// if(sendstream!=null){
		// try {
		// sendstream.writeObject(msg);
		// sendstream.flush();
		//
		// System.out.println("send message to "+target+" ok!"+"message content
		// :"+msg.messageContent+"succeed !");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }else{
		// System.out.println("target stream null !");
		// }

		Set<Integer> key = socketlist.keySet();
		System.out.println("个数"+key.size());
		for (Integer keyName : key) {

			System.out.println("键名：" + keyName);
			System.out.println("read target uid in set :" + keyName.intValue());
			if (keyName.intValue() == target) {
				

				ObjectOutputStream sendstream = (ObjectOutputStream) socketlist.get(keyName);
				try {
					sendstream.writeObject(msg);
					sendstream.flush();

					System.out.println("send message to " + target + " ok!" + "message content :" + msg.messageContent
							+ "succeed !");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	// Iterator<Map.Entry<Integer, ObjectOutputStream>> list =
	// socketlist.entrySet().iterator();
	// while (list.hasNext()) {
	//
	// Map.Entry<Integer, ObjectOutputStream> entry = list.next();
	// System.out.println("socket 1 !");
	// if(entry.getKey().intValue()==target){
	// ObjectOutputStream sendstream = entry.getValue();
	// try {
	// sendstream.writeObject(msg);
	// sendstream.flush();
	//
	// System.out.println("send message to "+target+" ok!"+"message content
	// :"+msg.messageContent+"succeed !");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// break;
	// }
	//
	// //ObjectOutputStream sendstream = (ObjectOutputStream)entry.getValue();
	// }

	// Set<Integer> key = socketlist.keySet();
	// Iterator<Integer> integerDemo = key.iterator();
	// while (integerDemo.hasNext()) {
	// Integer i = integerDemo.next();
	// System.out.println("read target uid in set :"+i.intValue());
	// if(i.intValue()==target){
	//
	// ObjectOutputStream sendstream = (ObjectOutputStream)socketlist.get(i);
	// try {
	// sendstream.writeObject(msg);
	// sendstream.flush();
	//
	// System.out.println("send message to "+target+" ok!"+"message content
	// :"+msg.messageContent+"succeed !");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// break;
	// }
	// }
	//
	// System.out.println("send message to "+target+"message content
	// :"+msg.messageContent);
	// }

}
