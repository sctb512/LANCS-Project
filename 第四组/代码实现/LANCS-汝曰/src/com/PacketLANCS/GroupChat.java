package com.PacketLANCS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//���հ�
public class GroupChat extends JFrame {
	int userID;
	static String nickname;
	private static final long serialVersionUID = 1L;
	//public AbstractButton tefasong;
	//public AbstractButton tejiaoliu;
	static JTextArea tejiaoliu;//�����ı���
	
	public GroupChat(int userID,String nickname,MainFrame mainframe) throws  IOException {
		this.userID=userID;
		GroupChat.nickname=nickname;
		JLabel latouxiang;// Ⱥͷ��
		JLabel beijingtupian;//����ͼƬ
		JButton bufasong;// ���Ͱ�ť
		//JButton buwenjian;//�ļ���ť
		JLabel tequnName;// Ⱥ�����ı���
		JTextArea tefasong;// �����ı���
		//JTextArea.SCROLLBARS_VERTICAL_ONLY;
		JLabel tesoushuo;// �����ı���
		
		JPanel p1, p2, p3, p4;
        //���ñ߿�ĸ�ʽ
		setSize(726, 590);
		setLayout(null);
		setLocation(180, 76);
        
        p1 = new JPanel();
		p1.setLayout(null);
		p1.setOpaque(false);
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setOpaque(false);
	    p3 = new JPanel();
		p3.setLayout(null);
		p3.setOpaque(false);
		p4 = new JPanel();
		p4.setLayout(null);
		p4.setOpaque(false);
		
		//���ø���panle�Ĵ�С����
		p1.setBounds(0, 0,720, 80);
		p2.setBounds(0, 80, 480, 315);
		p3.setBounds(0, 395, 480, 155);
		p4.setBounds(480, 80, 258, 410);
        //�����ı��Ŀ�Ĵ�С����
		tequnName = new JLabel();
		tequnName.setBounds(80, 20, 360, 40);
		tequnName.setText("��ӭ����Ⱥ��������");
		tequnName.setFont(new java.awt.Font("Dialog", 1, 26));
		p1.add(tequnName);
		
		//���͵��ı������ϸ
		tefasong = new JTextArea();
		JScrollPane jsp1 = new JScrollPane(tefasong); 
		
//		jsp1.setVerticalScrollBarPolicy(
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tefasong.setFont(new Font("г��",Font.BOLD|Font.ITALIC,16));//�����ı�д�������
		tefasong.setTabSize(10);
		tefasong.setRows(3);
		tefasong.setLineWrap(true);//�����Զ����й���
		tefasong.setWrapStyleWord(true);//������в����ֹ���
		tefasong.setBounds(0,  0, 482, 94);
		
		//�����ı������ϸ
		
		
		JScrollPane jsp2 = new JScrollPane(tefasong);
		jsp2.setBounds(0, 0, 480, 316);
		jsp2.setBackground(Color.CYAN);
		
		
		
		
		tejiaoliu = new JTextArea();
		tejiaoliu.setBounds(new Rectangle(480, 316));
		tejiaoliu.setLineWrap(true);//�����Զ����й��� 
		tejiaoliu.setWrapStyleWord(true);//������в����ֹ��� 
		tejiaoliu.setEditable(false);
		//tejiaoliu.setFont(new Font("г��",Font.BOLD|Font.ITALIC,16));//�����ı�д�������

		
		
		//jsp2.add(tejiaoliu);	
		jsp2.setViewportView(tejiaoliu);
		
		p2.add(jsp2);//��ӹ�����
		p3.add(tefasong);
		p3.add(jsp1);//������ο�
		
		tesoushuo = new JLabel();
		tesoushuo.setBounds(0, 0, 230, 40);
		tesoushuo.setText("  ��ǰ�����û���");
		tesoushuo.setFont(new java.awt.Font("Dialog", 1, 16));
		tesoushuo.setOpaque(true);
		tesoushuo.setBackground(Color.lightGray);;
		p4.add(tesoushuo);
		
		//�����û��б�   (ͨ�������û�����)
		
		JScrollPane userList = new JScrollPane(new GroupListPane(userID));
		userList.setBounds(0, 40, 230, 400);
		p4.add(userList);
		
		
        
		//����ĸ�����ť��ͼƬ
		ImageIcon bgimge1=new ImageIcon(".\\img\\qutouxiang.png");
		latouxiang = new JLabel(bgimge1);
		latouxiang.setOpaque(false);
		latouxiang.setBounds(0, 0, 75, 80);
		ImageIcon bgimge2=new ImageIcon(".\\img\\wenjian1.png");
//		buwenjian = new JButton(bgimge2);
//		buwenjian.setBorderPainted(false);
//		buwenjian.setBounds(370, 100, 40, 40);
		//ImageIcon bgimge4=new ImageIcon(".\\img\\fasong1.png");
		bufasong = new JButton("����");
		bufasong.setBounds(420, 100, 60, 30);
		bufasong.setForeground(Color.WHITE);
		bufasong.setOpaque(true);
		bufasong.setBackground(Color.darkGray);
		bufasong.setBorderPainted(false);
		ImageIcon bgimge5=new ImageIcon(".\\img\\beijing.png");
		beijingtupian = new JLabel(bgimge5);
		beijingtupian.setBounds(0, 0, 746, 592);
		
		
		//��ư�ť�¼���������ͣ����ͷ����ı�������
		bufasong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String gmsg = tefasong.getText();
				if(gmsg.equals("")){
					
				}else{
					
					ChatMessage chatmsg = new ChatMessage("groupchat");
					chatmsg.sourceUid = userID;
					chatmsg.messageContent = gmsg;
					chatmsg.nickname=nickname;
					chatmsg.targetUid = 1111111111;
					
					
					//��̬Ҫ˼��һ��
					mainframe.getChatTcp().sendmsg(chatmsg);
					
					tefasong.setText("");
					
					System.out.println("send nickname :"+nickname);
				}	
			}
				

});
		tefasong.setText(null);
		

		p1.add(latouxiang);

		
		Container contentPane = this.getContentPane();
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);
		contentPane.add(beijingtupian);
		p3.add(bufasong);
		//p3.add(buwenjian);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
	}
	
	public static void Messageupdata(String nickname,String msg){
		if(GroupChat.nickname.equals(nickname)){
			tejiaoliu.append("��˵��"+msg+"\r\n");
			tejiaoliu.setFont(new Font("г��",Font.ITALIC,20));//�����ı�д�������
			tejiaoliu.setForeground(Color.GREEN);
			tejiaoliu.setCaretPosition(tejiaoliu.getText().length()); 
		}else{
		tejiaoliu.append(nickname+"˵��"+msg+"\r\n");
		tejiaoliu.setFont(new Font("г��",Font.BOLD,20));//�����ı�д�������
		tejiaoliu.setForeground(Color.BLACK);
		tejiaoliu.setCaretPosition(tejiaoliu.getText().length()); 
		}
	}
	

}
class GroupMemberModel {

	public JButton jButton = null;// ��ʾ����ͷ��

	public JPanel jPanel = new JPanel();// ģ��������

	private JLabel lb_nickName = null;// ��ʾ�ǳƣ�

	private int pic;

	private String nickname = null;
	




	public GroupMemberModel(int pic, String nickname, int len) {
		super();
		this.pic = pic;// ͷ��ࣨ�ж��ַ�������ʵ�֣�������򵥣�
		this.nickname = nickname;// �ǳƣ�
		this.nickname = nickname;

		initialize();
	}

	private void initialize() {
		
		lb_nickName = new JLabel();
		lb_nickName.setBounds(new Rectangle(52, 5, 80, 30));
		lb_nickName.setFont(new Font("Dialog", Font.BOLD, 14));
		lb_nickName.setText(nickname);
		jPanel.setSize(new Dimension(320, 40));
		jPanel.setLayout(null);
		jPanel.add(getJButton(), null);
		jPanel.add(lb_nickName, null);
		jPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();// ����Ƴ�ģ�������ı䱳����ɫ��
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();// ����ƽ�ģ�������ı䱳����ɫ��
			}
		});
	}

	private void exchangeEnter() {
		jPanel.setBackground(new Color(192, 224, 248));
	}

	private void exchangeExited() {
		jPanel.setBackground(null);
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(8, 5, 30, 30));
			jButton.setBackground(new Color(236, 255, 236));
			jButton.setIcon(new ImageIcon("././img/qqIcon" + pic + ".png"));
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseExited(java.awt.event.MouseEvent e) {
					exchangeExited();// ����Ƴ�ģ�������ı䱳����ɫ��
				}

				public void mouseEntered(java.awt.event.MouseEvent e) {
					exchangeEnter();// ����ƽ�ģ�������ı䱳����ɫ��
				}
			});

		}
		return jButton;
	}
}

class GroupListPane extends JPanel {

	private static final long serialVersionUID = 1L;

	
	// 300*510

	public GroupListPane(int userID) {
		super();
		initialize(userID);
	}

	private void initialize(int userID) {
		

		JLabel[] friend = new JLabel[100];
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(260, 408);
		this.setLocation(20, 5);

		updataUsers(friend,userID,this);
		
	}
	public static void updataUsers(JLabel[] friend,int userID,GroupListPane thisPane){
		// �����û���Ϣ
				SocketThread clientSocket = new SocketThread();
		
				Message msg = new Message("grouplist");

				msg.setIfo(userID);

				clientSocket.send(msg);
				Message listResult = (Message) clientSocket.getResult();

				FriendsIfo[] friends = listResult.friendlist;
				if (friends == null) {
					System.out.println("friends null !");
				} else {
					int i=0;
					while (friends[i]!=null) {
						friend[i] = new JLabel();
						friend[i].setIcon(new ImageIcon("././img/groupuserbg.png"));
						friend[i].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
						friend[i].add(new GroupMemberModel(1, friends[i].NickName, 200).jPanel);
						friend[i].setVisible(true);
						thisPane.add(friend[i], null);
						i+=1;
					}
				}
	}
}