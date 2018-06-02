package com.PacketLANCS;



import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.*;
//最终版
public class FriendIfoFrame {  //资料页面
	JFrame f; 
	ImageIcon bImage;
	ImageIcon tImage;
	JLabel nickname;
	JLabel accountnumber1;
	JLabel accountnumber;
	JLabel QQ1;
	JLabel QQ;
	JLabel sign;
	JLabel sign1;
	
	public FriendsIfo friendIfo;
	
	public FriendIfoFrame(FriendsIfo friendIfo){
		//窗体f
		f=new JFrame("我的资料");
		Container contentPane=f.getContentPane();
		contentPane.setLayout(null);
		f.setSize(500, 346);
		f.setResizable(false);
		
		bImage = new ImageIcon("././img/if.gif");
		JLabel bg = new JLabel(bImage);
		bg.setBounds(new Rectangle(0, 0, 600, 386));
        bg.setOpaque(false);
        
		tImage = new ImageIcon("././img/tx"+friendIfo.img+".gif");
		JLabel bg2 = new JLabel(tImage);
		bg2.setBounds(new Rectangle(60, 50, 120, 120));
		bg.setOpaque(false);
        contentPane.add(bg2);
		
		accountnumber1=new JLabel("123456789");
		accountnumber1.setBounds(290, 50, 140, 20);
		accountnumber1.setFont(new java.awt.Font("Dialog",0,20));
		accountnumber1.setText(String.valueOf(friendIfo.Uid));
		contentPane.add(accountnumber1);
		
		accountnumber=new JLabel("账号:");
		accountnumber.setBounds(230, 50, 60, 20);
		accountnumber.setFont(new java.awt.Font("Dialog",0,20));
		contentPane.add(accountnumber);
		
		QQ1=new JLabel("9875432120");
		QQ1.setBounds(290, 100, 140, 20);
		QQ1.setFont(new java.awt.Font("Dialog",0,20));
		QQ1.setText(String.valueOf(friendIfo.QQ));
		contentPane.add(QQ1);
		
		QQ=new JLabel("Q Q:");
		QQ.setBounds(230, 100, 60, 20);
		QQ.setFont(new java.awt.Font("Dialog",0,20));
		contentPane.add(QQ);
		
		nickname=new JLabel("点点");
		nickname.setBounds(100, 210, 40, 20);
		nickname.setFont(new java.awt.Font("Dialog",0,20));
		nickname.setText(friendIfo.NickName);
		nickname.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(nickname);
		
		
		sign1=new JLabel("签名:");
		sign1.setBounds(230, 208, 60, 20);
		sign1.setFont(new java.awt.Font("Dialog",0,20));
		contentPane.add(sign1);
		
		
		sign=new JLabel("床前明月光，疑是地上霜。");
		sign.setBounds(290, 192, 240, 50);
		sign.setFont(new java.awt.Font("Dialog",0,20));
		sign.setText(friendIfo.Sign);
		contentPane.add(sign);
		
		
		
		contentPane.add(bg);
		f.setLocationRelativeTo(null);
		f.setVisible(false);
		
	}
	
	public void display(){
		f.setVisible(true);
	}

}

