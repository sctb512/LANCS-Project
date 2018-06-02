package com.PacketLANCS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//最终版
public class Register implements ActionListener {

	JFrame f; // 注册窗体
	JButton ZCbutton, BCbutton;
	JTextField nickname, username, sign, qq; // 昵称框,用户名框,个性签名框,QQ框
	JPasswordField password, confirm; // 密码框，确认密码框
	int Qdetails;
	String Ndetails, Udetails, Pdetails, Cdetails, Sdetails; // 用于接受各文本框中的内容
	ImageIcon bg, bg2;
	JLabel notice;

	public void Launch() {
		f = new JFrame("注册"); // 窗体名
		Container contentpane = f.getContentPane();
		contentpane.setLayout(null); // 把默认布局管理器置空

		bg = new ImageIcon(".\\img\\bg1.jpg"); // 背景图片
		JLabel label = new JLabel(bg); // 把背景图片显示在一个标签里面
		label.setBounds(0, 0, 530, 620); // 把标签的大小位置设置为图片刚好填充整个面板
		nickname = new JTextField();
		username = new JTextField();
		password = new JPasswordField();
		confirm = new JPasswordField();
		sign = new JTextField();
		qq = new JTextField();

		Ndetails = nickname.getText(); // 接收文本框内容
		Udetails = username.getText();
		Pdetails = String.valueOf(password.getPassword());
		Cdetails = String.valueOf(confirm.getPassword());

		JLabel Nlabel = new JLabel("昵          称:");
		JLabel Ulabel = new JLabel("用  户   名:");
		JLabel Plabel = new JLabel("密          码:");
		JLabel Clabel = new JLabel("确认密码:");
		JLabel Slabel = new JLabel("个性签名:");
		notice = new JLabel(); // 设置提示
		LimitedDocument limitDocument = new LimitedDocument(10); // 允许长度为10
		limitDocument.setAllowChar("0123456789"); // 只允许输入数字
		qq.setDocument(limitDocument);
		JLabel Qlabel = new JLabel("Q         Q：");
		ZCbutton = new JButton("注      册"); // 点击注册
		ZCbutton.addActionListener(this);

		contentpane.add(Nlabel);
		contentpane.add(Ulabel);
		contentpane.add(Plabel);
		contentpane.add(Clabel);
		contentpane.add(Slabel);
		contentpane.add(Qlabel);
		contentpane.add(nickname);
		contentpane.add(username);
		contentpane.add(password);
		contentpane.add(confirm);
		contentpane.add(sign);
		contentpane.add(qq);
		contentpane.add(notice);
		contentpane.add(ZCbutton);

		contentpane.add(label);
		System.out.println(label);
		nickname.setBounds(100, 70, 200, 30);
		username.setBounds(100, 110, 200, 30);
		password.setBounds(100, 150, 200, 30);
		confirm.setBounds(100, 190, 200, 30);
		sign.setBounds(100, 230, 200, 30);
		qq.setBounds(100, 270, 200, 30);
		Nlabel.setBounds(30, 70, 60, 30);
		Ulabel.setBounds(30, 110, 60, 30);
		Plabel.setBounds(30, 150, 60, 30);
		Clabel.setBounds(30, 190, 60, 30);
		Slabel.setBounds(30, 230, 60, 30);
		Qlabel.setBounds(30, 270, 60, 30);

		notice.setBounds(100, 300, 200, 20);
		notice.setForeground(Color.red);
		notice.setHorizontalAlignment(SwingConstants.CENTER); // 设置居中

		ZCbutton.setOpaque(false); // 设置按钮不透明
		ZCbutton.setBounds(100, 320, 200, 30);

		f.setBounds(200, 220, 350, 450);
		f.setResizable(false); // 窗体设置不可改变大小
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭时程序结束
		f.setVisible(true); // 设置窗体可见
	}

	// 注册监听器，点击发生以下事件
	public void actionPerformed(ActionEvent e) {
		if (username.getText().length() < 1 || password.getPassword().length < 1) {
			notice.setText("用户名或密码为空");
			return;
		} else if (confirm.getPassword().length < 1) {
			notice.setText("请确认密码");
			return;
		} else if (password.getPassword().length != confirm.getPassword().length) {
			notice.setText("两次输入密码不同，请重新输入");
			return;
		} else {
			Ndetails = nickname.getText(); // 接收文本框内容
			Udetails = username.getText();
			Pdetails = String.valueOf(password.getPassword());
			Cdetails = String.valueOf(confirm.getPassword());
			Sdetails = sign.getText();
			Qdetails = Integer.valueOf(qq.getText());
			SocketThread clientSocket = new SocketThread();

			Message msg = new Message("reg");
			msg.setIfo(Ndetails, Udetails, Pdetails, Sdetails, Qdetails);

			clientSocket.send(msg);

			this.f.setVisible(false);

			// 待修改
			Message regResult;
			int regIfoValue;
			regResult = clientSocket.getResult();
			regIfoValue = regResult.value;
			if (regIfoValue == 1) {

				JFrame newF = new JFrame("");
				newF.setLayout(null);
				ImageIcon bg2 = new ImageIcon(".\\img\\bg2.jpg");
				newF.setUndecorated(true);
				JLabel bglabel = new JLabel(bg2);
				bglabel.setBounds(0, 0, 300, 200);
				JButton Nbutton = new JButton("点击登录");
				Nbutton.setBounds(100, 100, 100, 30);
				Nbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						newF.setVisible(false);
					}
				});
				JLabel notice = new JLabel();
				notice.setBounds(96, 40, 120, 50);
				notice.setText("注册成功！");
				notice.setHorizontalTextPosition(SwingConstants.CENTER);
				notice.setFont(new java.awt.Font("Dialog", 1, 22));

				newF.add(notice);

				newF.add(Nbutton);
				newF.getContentPane().add(bglabel);
				newF.setSize(300, 200);
				newF.setVisible(true);
				newF.setResizable(false);
				newF.setLocationRelativeTo(null);
				newF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else if (regIfoValue == 0) {
				notice.setText("注册失败，请重试！");
			}
		}
	}
}
