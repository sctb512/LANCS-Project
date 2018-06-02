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

//���հ�
public class Register implements ActionListener {

	JFrame f; // ע�ᴰ��
	JButton ZCbutton, BCbutton;
	JTextField nickname, username, sign, qq; // �ǳƿ�,�û�����,����ǩ����,QQ��
	JPasswordField password, confirm; // �����ȷ�������
	int Qdetails;
	String Ndetails, Udetails, Pdetails, Cdetails, Sdetails; // ���ڽ��ܸ��ı����е�����
	ImageIcon bg, bg2;
	JLabel notice;

	public void Launch() {
		f = new JFrame("ע��"); // ������
		Container contentpane = f.getContentPane();
		contentpane.setLayout(null); // ��Ĭ�ϲ��ֹ������ÿ�

		bg = new ImageIcon(".\\img\\bg1.jpg"); // ����ͼƬ
		JLabel label = new JLabel(bg); // �ѱ���ͼƬ��ʾ��һ����ǩ����
		label.setBounds(0, 0, 530, 620); // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		nickname = new JTextField();
		username = new JTextField();
		password = new JPasswordField();
		confirm = new JPasswordField();
		sign = new JTextField();
		qq = new JTextField();

		Ndetails = nickname.getText(); // �����ı�������
		Udetails = username.getText();
		Pdetails = String.valueOf(password.getPassword());
		Cdetails = String.valueOf(confirm.getPassword());

		JLabel Nlabel = new JLabel("��          ��:");
		JLabel Ulabel = new JLabel("��  ��   ��:");
		JLabel Plabel = new JLabel("��          ��:");
		JLabel Clabel = new JLabel("ȷ������:");
		JLabel Slabel = new JLabel("����ǩ��:");
		notice = new JLabel(); // ������ʾ
		LimitedDocument limitDocument = new LimitedDocument(10); // ������Ϊ10
		limitDocument.setAllowChar("0123456789"); // ֻ������������
		qq.setDocument(limitDocument);
		JLabel Qlabel = new JLabel("Q         Q��");
		ZCbutton = new JButton("ע      ��"); // ���ע��
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
		notice.setHorizontalAlignment(SwingConstants.CENTER); // ���þ���

		ZCbutton.setOpaque(false); // ���ð�ť��͸��
		ZCbutton.setBounds(100, 320, 200, 30);

		f.setBounds(200, 220, 350, 450);
		f.setResizable(false); // �������ò��ɸı��С
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ʱ�������
		f.setVisible(true); // ���ô���ɼ�
	}

	// ע���������������������¼�
	public void actionPerformed(ActionEvent e) {
		if (username.getText().length() < 1 || password.getPassword().length < 1) {
			notice.setText("�û���������Ϊ��");
			return;
		} else if (confirm.getPassword().length < 1) {
			notice.setText("��ȷ������");
			return;
		} else if (password.getPassword().length != confirm.getPassword().length) {
			notice.setText("�����������벻ͬ������������");
			return;
		} else {
			Ndetails = nickname.getText(); // �����ı�������
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

			// ���޸�
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
				JButton Nbutton = new JButton("�����¼");
				Nbutton.setBounds(100, 100, 100, 30);
				Nbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO �Զ����ɵķ������
						newF.setVisible(false);
					}
				});
				JLabel notice = new JLabel();
				notice.setBounds(96, 40, 120, 50);
				notice.setText("ע��ɹ���");
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
				notice.setText("ע��ʧ�ܣ������ԣ�");
			}
		}
	}
}
