package com.PacketLANCS;



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ToOneChatRoom {
	
	JTextArea meaasgearea;
	MainFrame mainframe;
	public static Map<Integer,ToOneChatRoom> friendlistmap = new HashMap<Integer,ToOneChatRoom>();
	
	
	JFrame c;
	JPanel chat1, chat2,chat3;
	JLabel head, nickname, signatrue, head1, head2, content1, content2;
	JTextField news;
	JButton files, send;
	ImageIcon img_bg;
	ImageIcon img_bg2;
	
	int masterUid;
	FriendsIfo friendIfo;
	String nickname2;
	
	public ToOneChatRoom(int masterUid,String nickname2, FriendsIfo friendIfo ,MainFrame mainframe){
		this.masterUid=masterUid;
		this.friendIfo=friendIfo;
		this.mainframe=mainframe;
		this.nickname2=nickname2;
		friendlistmap.put(friendIfo.Uid, this);
		
	}
	
	public void launch() {
		
		FriendIfoFrame ifo = new FriendIfoFrame(friendIfo);
		// �������촰��
		JFrame c = new JFrame("Chat Room");
		Container contentPane = c.getContentPane();
//		c.setUndecorated(true);
		contentPane.setLayout(null);
		c.setSize(700, 590);
		c.setResizable(false);
		c.setLocation(180, 76);
		
		// ��ӱ���
		img_bg = new ImageIcon("././img/chatting.gif");
		JLabel bg = new JLabel(img_bg);
		bg.setBounds(new Rectangle(0, 0, 700, 620));
        bg.setOpaque(false);
		
		// ����chat1һ��Panel
		JPanel chat1 = new JPanel();
		chat1.setLayout(null);
		chat1.setBounds(0, 0, 700, 145);
		chat1.setOpaque(false);//����Panel�ı���͸��
		
		contentPane.add(chat1);		
		// ���öԷ�ͷ���Label
		JButton head = new JButton("ͷ��");
		head.setBounds(20, 13, 80, 80);
		head.setOpaque(true);

		head.setIcon(new ImageIcon("././img/chatIco"+friendIfo.img+".png"));
		head.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ifo.display();
				
			}
		});
		
		chat1.add(head);
		// ���öԷ��ǳƵ�Label
		JLabel nickname = new JLabel("�ǳ�");
		nickname.setBounds(140, 20, 200, 53);
		nickname.setFont(new java.awt.Font("Dialog", 1, 24));

		chat1.add(nickname);
		// ���öԷ�����ǩ����Label
		JLabel signatrue = new JLabel("����ǩ��");
		signatrue.setBounds(145, 60, 400, 33);

		chat1.add(signatrue);
		
		//����������Ϣ
		nickname.setText(friendIfo.NickName);
		signatrue.setText(friendIfo.Sign);
		
		
		meaasgearea = new JTextArea();
		JScrollPane msgscroll = new JScrollPane(meaasgearea); 
		

		meaasgearea.setFont(new Font("г��",Font.BOLD|Font.ITALIC,16));//�����ı�д�������
		meaasgearea.setTabSize(10);
		meaasgearea.setRows(3);
		meaasgearea.setLineWrap(true);//�����Զ����й���
		meaasgearea.setWrapStyleWord(true);//������в����ֹ���
		meaasgearea.setBounds(0,  2, 658, 316);
		
		//�����ı������ϸ
		
		
		msgscroll.setBounds(20, 0, 658, 316);
		msgscroll.setBackground(Color.CYAN);
		
		
		
		
		//meaasgearea = new JTextArea();
		meaasgearea.setBounds(new Rectangle(580, 316));
		meaasgearea.setLineWrap(true);//�����Զ����й��� 
		meaasgearea.setWrapStyleWord(true);//������в����ֹ��� 
		meaasgearea.setEditable(false);
		//meaasgearea.setFont(new Font("г��",Font.BOLD|Font.ITALIC,16));//�����ı�д�������

		
		//jsp2.add(tejiaoliu);	
		msgscroll.setViewportView(meaasgearea);
		
		// ����chat2һ��Panel
		JPanel chat2 = new JPanel();
		chat2.setLayout(null);
		chat2.setBounds(0, 105, 700, 500);
		chat2.setOpaque(false);
		chat2.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(chat2);
		
		chat2.add(msgscroll);//��ӹ�����
		
//		// ����chat2һ��Panel
//		JPanel chat2 = new JPanel();
//		chat2.setLayout(null);
//		chat2.setBounds(0, 105, 700, 400);
//		chat2.setOpaque(false);
//		chat2.setBorder(BorderFactory.createLineBorder(Color.black));
//		contentPane.add(chat2);
//		//���öԻ�ʱ�Է�ͷ���һ��Label
//		JLabel head1 = new JLabel("�Է�", JLabel.CENTER);
//		head1.setBounds(50, 35, 50, 50);
//		head1.setBorder(BorderFactory.createLineBorder(Color.black));
//		chat2.add(head1);
//		//���öԻ�ʱ�Լ�ͷ���һ��Label
//		JLabel head2 = new JLabel("�Լ�", JLabel.CENTER);
//		head2.setBounds(596, 115, 50, 50);
//		head2.setBorder(BorderFactory.createLineBorder(Color.black));
//		chat2.add(head2);
//		//���öԻ�ʱ�Է���Ϣ��һ��Label
//		JLabel content1 = new JLabel("lalalala");
//		content1.setBounds(133, 35, 200, 50);
//		content1.setBorder(BorderFactory.createLineBorder(Color.black));
//		chat2.add(content1);
//		//���öԻ�ʱ�Լ���Ϣ��һ��Label
//		JLabel content2 = new JLabel("yoyoyyooyoy");
//		content2.setBounds(367, 115, 200, 50);
//		content2.setBorder(BorderFactory.createLineBorder(Color.black));
//		chat2.add(content2);
//		
//		// ����chat3һ��Panel
		JPanel chat3 = new JPanel();
	    chat3.setLayout(null);
		chat3.setBounds(0, 405, 700, 145);
		chat3.setOpaque(false);
		JTextArea news = new JTextArea();
		news.setBounds(20, 22, 658,80);

		chat3.add(news);
		
		contentPane.add(chat3);
		// ������д��Ҫ������Ϣ��JTextField
		
		
		
		
		// ���÷����ļ��ͷ�����Ϣ������Button
//		JButton files = new JButton("�ļ�");
//		files.setBounds(581, 105, 40, 30);
//		files.setBorder(BorderFactory.createLineBorder(Color.black));
//		chat3.add(files);
//		files.setOpaque(false);
		JButton send = new JButton(" ��    �� ");
		send.setBounds(628, 105, 50, 30);
		send.setBorder(BorderFactory.createLineBorder(Color.black));
		//�¼�������
		send.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						String gmsg = news.getText();
						if(gmsg.equals("")){
							System.out.println("msg null");
						}else{
							
							ChatMessage chatmsg = new ChatMessage("groupchat");
							chatmsg.sourceUid = masterUid;
							chatmsg.messageContent = gmsg;
							chatmsg.nickname=nickname2;
							chatmsg.targetUid = friendIfo.Uid;
							
							//TCPThread.sendmsgstatic(chatmsg);
							System.out.println();
							mainframe.getChatTcp().sendmsg(chatmsg);
							meaasgearea.setFont(new Font("г��",Font.ITALIC,20));//�����ı�д�������
							meaasgearea.setForeground(Color.GREEN);
							meaasgearea.append("��˵��"+gmsg+"\r\n");
							
						
							news.setText("");
							        
							System.out.println("send nickname :"+nickname+"mastereuid"+masterUid+"targetuid"+friendIfo.Uid);
						}	
					}
						

		});
		
		chat3.add(send);
		send.setOpaque(false);
		
		contentPane.add(bg);
		
		c.setLocationRelativeTo(null);
		c.setVisible(true);
	
		/* ���Ӵ��������¼� */
		c.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.dispose();
				super.windowClosing(e);
			}
		});
	}
	
	public void Messageupdata(String nickname,String msg){
		
		meaasgearea.setFont(new Font("г��",Font.BOLD,20));//�����ı�д�������
		meaasgearea.setForeground(Color.BLACK);
		meaasgearea.append(nickname+"˵��"+msg+"\r\n");
		meaasgearea.setCaretPosition(meaasgearea.getText().length()); 
		System.out.println(nickname+" : "+msg);
		System.out.println("news update !");
	}

	
}

