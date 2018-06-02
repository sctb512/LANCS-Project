package com.PacketLANCS;

import java.io.Serializable;

//×îÖÕ°æ
public class FriendsIfo implements Serializable{

	private static final long serialVersionUID = 1L;
	public int img;

	public String NickName;
	public String UserName;
	public String Sign;
	public int QQ;
	public int Uid;

	public FriendsIfo(int Uid,String UserName,String NickName,String Sign,int img,int QQ){
		this.Uid=Uid;
		this.UserName=UserName;
		this.NickName=NickName;
		this.Sign=Sign;
		this.img=img;
		this.QQ=QQ;
	}
}