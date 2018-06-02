package com.PacketLANCS;

import java.io.Serializable;

public class ChatMessage implements Serializable {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2L;	
	String flag;
	public int sourceUid;
	public int targetUid;
	public String nickname;
	public String messageContent;
	public ChatMessage(String flag){
		this.flag=flag;
	}
}
