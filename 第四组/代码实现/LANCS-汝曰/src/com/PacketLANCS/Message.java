package com.PacketLANCS;

import java.io.Serializable;

//×îÖÕ°æ

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	public String flag = null;
	public String NickName;
	public String UserName;
	public String PassWord;
	public String Sign;
	public int QQ;
	public int value;
	public int Uid;
	public int img;
	public int targetid;
	FriendsIfo[] friendlist;
	
	public Message(String flag){
		this.flag=flag;
	}
	public void setIfo(String NickName, String UserName, String PassWord, String Sign,
			int QQ) {
		this.NickName = NickName;
		this.UserName = UserName;
		this.PassWord = PassWord;
		this.Sign = Sign;
		this.QQ = QQ;
	}
	public void setIfo(int value) {
		this.value = value;
	}
	public void setIfo(String UserName,String PassWord){
		this.UserName=UserName;
		this.PassWord=PassWord;
	}
	public void setIfo(int value,int Uid) {
		this.value = value;
		this.Uid=Uid;
	}
	public void setIfo(int img,String nickName,String sign){
		this.img=img;
		this.NickName=nickName;
		this.Sign=sign;
	}
	public void setIfo(FriendsIfo[] friendlist) {
		this.friendlist=friendlist;
		
	}
	public void setIfo(int img,String nickName,String sign, int Uid, String UserName, int QQ){
		this.img=img;
		this.NickName=nickName;
		this.Sign=sign;
		this.Uid=Uid;
		this.UserName=UserName;
		this.QQ = QQ;
	}
	public void setUid(int Uid) {
		this.Uid=Uid;
		
	}
	
	

}

