package com.PacketLANCS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//最终版
//此线程负责与客户端联系
public class SocketThread{
	Socket clientSocket;
	public ObjectOutputStream sendStream;
	public ObjectInputStream acceptStream;
	
	Message resultMsg;
	
	public SocketThread(){
		try {
			clientSocket = new Socket("119.29.221.132", 1200);
			sendStream = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public void send(Message msg) {
		try {
			sendStream.writeObject(msg);
			sendStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Message getResult() {
		
		try {
			acceptStream=new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			resultMsg = (Message) acceptStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println("socket read :"+acceptStream);
	    try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return resultMsg;

	}
	
}
