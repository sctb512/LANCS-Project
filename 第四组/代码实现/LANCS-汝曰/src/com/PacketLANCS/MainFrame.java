package com.PacketLANCS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

class MainFrame extends JFrame {

	private int userID;
	String nickname;
	TCPThread chatTcp;


	public TCPThread getChatTcp() {
		return chatTcp;
	}

	public void setChatTcp(TCPThread chatTcp) {
		this.chatTcp = chatTcp;
	}

	// 300*510
	public MainFrame(int userID) {
		this.userID = userID;
		chatTcp = new TCPThread(userID);
	}

	private static final long serialVersionUID = 1L;
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
	// ���ڴ����϶��¼�����ʾ��갴��ʱ�����꣬�����JFrame
	int xOld = 0;
	int yOld = 0;

	public void Launch() {

		System.out.println(userID);
		
		//������Ϣ�������
		chatTcp.start();
		setSize(300, 560);
		setLayout(null);
		setLocation(920, 90);;
		;

		// �����϶��¼�
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				setLocation(xx, yy);
			}
		});

		// JLayeredPane�����������ͼ��ģ�һ�����ڱ�����һ�����ڽ���
		JLayeredPane layeredPane = getLayeredPane();
		// layeredPane.setBounds(0, 0, 400, 300);
		// add(layeredPane);

		// ����Panel
		JPanel bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 300, 560);
		bgPanel.setOpaque(false);

		// ����ͼƬ����ӵ�����Panel����
		JLabel bgLabel = new JLabel(new ImageIcon("././img/mainbgimg.png"));
		bgPanel.add(bgLabel);

		// �����棬Ҳ���Ǳ��������һ��Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 300, 560);
		mainPanel.setLayout(null);

		// ��С����ť
		JButton minButton = new JButton();
		minButton.setContentAreaFilled(false);
		minButton.setBorder(null);
		minButton.setIcon(new ImageIcon("././img/minIcon.png"));
		minButton.setBounds(260, 0, 20, 20);
		minButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miniTray();
				setVisible(false);
			}
		});

		minButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				minButton.setIcon(new ImageIcon("././img/minIcon1.png"));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				minButton.setIcon(new ImageIcon("././img/minIcon.png"));

			}

		});

		mainPanel.add(minButton);

		// �رհ�ť
		JButton closeButton = new JButton();
		closeButton.setContentAreaFilled(false);
		closeButton.setBorder(null);
		closeButton.setIcon(new ImageIcon("././img/closeButton.png"));
		closeButton.setBounds(280, 0, 20, 20);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				closeButton.setIcon(new ImageIcon("././img/closeButton1.png"));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				closeButton.setIcon(new ImageIcon("././img/closeButton.png"));

			}

		});

		mainPanel.add(closeButton);

		// �û�ͷ��

		JButton headImg = new JButton();
		headImg.setBounds(24, 46, 70, 70);
		headImg.setBackground(Color.GREEN);

		headImg.setIcon(new ImageIcon("././img/photoImg.png"));

		mainPanel.add(headImg);

		// �ǳ�

		JLabel nickName = new JLabel();
		nickName.setBounds(115, 50, 100, 30);
		nickName.setBackground(Color.GREEN);

		nickName.setFont(new Font("����", Font.BOLD, 22));
		nickName.setForeground(Color.WHITE);
		nickName.setText("������ҥ������");

		mainPanel.add(nickName);

		// ����ǩ��

		JLabel sign = new JLabel();
		sign.setBounds(115, 94, 160, 20);
		sign.setBackground(Color.GREEN);

		sign.setFont(new Font("����", Font.BOLD, 10));
		sign.setForeground(Color.WHITE);
		sign.setText("City of stars, Are you shining just for me?");

		mainPanel.add(sign);

		// �����û���Ϣ
		SocketThread clientSocket = new SocketThread();

		Message msg = new Message("ownifo");
		System.out.println(userID);
		msg.setUid(userID);
		
		clientSocket.send(msg);
		Message ownResult;
		ownResult = clientSocket.getResult();
		
		headImg.setIcon(new ImageIcon("././img/photo" + ownResult.img + ".png"));
		nickname=ownResult.NickName;
		nickName.setText(nickname);
		sign.setText(ownResult.Sign);
		
		FriendsIfo ifo = new FriendsIfo(ownResult.Uid,ownResult.UserName,ownResult.NickName,ownResult.Sign,ownResult.img,ownResult.QQ);
		//ת����Ϣ
		
		FriendIfoFrame headButton = new FriendIfoFrame(ifo);
		headImg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				headButton.display();
			}
		});
		
		// �����б�

		JButton friendList = new JButton();
		friendList.setBounds(0, 140, 100, 32);
	
		friendList.setContentAreaFilled(false);
		friendList.setIcon(new ImageIcon("././img/friends.png"));

		mainPanel.add(friendList);

		// �б�

		JPanel list = new JPanel();
		list.setBounds(0, 172, 300, 360);
		

		JScrollPane scrollPane = new JScrollPane(new ListPane(userID,nickname,this));
		scrollPane.setBackground(Color.ORANGE);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// ����ʾˮƽ������
		list.setLayout(new BorderLayout());
		list.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(list);

		// �����ı���

		JTextField searchText = new JTextField();
		searchText.setBounds(0, 532, 244, 28);
	
		searchText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchText.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				searchText.setText("�����û������˺ţ������Ӻ��ѣ�");
			}
		});

		mainPanel.add(searchText);

		// ������ť

		JButton searchButton = new JButton();
		searchButton.setBounds(244, 532, 56, 28);

		searchButton.setContentAreaFilled(false);
		searchButton.setIcon(new ImageIcon("././img/searchButton.png"));
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ChatMessage msg = new ChatMessage("addfriend");
				msg.sourceUid=userID;
				msg.targetUid=Integer.valueOf(searchText.getText());
				

				chatTcp.sendmsg(msg);
				
				Message addResult;
				addResult = (Message) clientSocket.getResult();

				if (addResult.value == 1) {
					System.out.println("add friend succeed");
					//new MainFrame(logResult.Uid).Launch();							
					setVisible(false);
				} else if (addResult.value == 0) {
					System.out.println("add friend failed !");
					//notice.setText("�˺Ż�����������������룡");
				} else {
					System.out.println("���ִ�������ϵ����Ա��");
					//notice.setText("���ִ�������ϵ����Ա��");
				}
				
			}
		});
		mainPanel.add(searchButton);

		// ������

		JButton chatRoom = new JButton();
		chatRoom.setBounds(100, 140, 100, 32);
		chatRoom.setContentAreaFilled(false);
		chatRoom.setIcon(new ImageIcon("././img/chatRoom.png"));
		
		chatRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GroupChat(userID,nickname,MainFrame.this);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});


		mainPanel.add(chatRoom);

		mainPanel.setOpaque(false);
		getContentPane().add(mainPanel);
		getContentPane().add(bgPanel);

		setUndecorated(true);// ���ر߿�
		setVisible(true);

	}

	private void miniTray() { // ������С��������������

		ImageIcon trayImg = new ImageIcon("././img/leida.png");// ����ͼ��

		PopupMenu pop = new PopupMenu(); // ���������һ��˵�
		MenuItem show = new MenuItem("��ԭ");
		MenuItem exit = new MenuItem("�˳�");

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // ���»�ԭ��

				tray.remove(trayIcon);
				setVisible(true);
				setExtendedState(JFrame.NORMAL);
				toFront();
			}

		});

		exit.addActionListener(new ActionListener() { // �����˳���

			public void actionPerformed(ActionEvent e) {

				tray.remove(trayIcon);
				System.exit(0);

			}

		});

		pop.add(show);
		pop.add(exit);

		trayIcon = new TrayIcon(trayImg.getImage(), "��Ի������ͨ��ϵͳ", pop);
		trayIcon.setImageAutoSize(true);

		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // �����˫���¼�

				if (e.getClickCount() == 2) {

					tray.remove(trayIcon); // ��ȥ����ͼ��
					setVisible(true);
					setExtendedState(JFrame.NORMAL); // ��ԭ����
					toFront();
				}

			}

		});

		try {
			tray.add(trayIcon);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

	}

}

class MemberModel {

	public JButton jButton = null;// ��ʾ����ͷ��

	public JPanel jPanel = new JPanel();// ģ��������

	private JLabel lb_nickName = null;// ��ʾ�ǳƣ�

	private int pic;

	private String nickname = null;
	
	private String sign = null;

	private JLabel lb_mood = null;// ��ʾ���飻

	public MemberModel(int pic, String nickname,String sign, int len) {
		super();
		this.pic = pic;// ͷ��ࣨ�ж��ַ�������ʵ�֣�������򵥣�
		this.nickname = nickname;// �ǳƣ�
		this.nickname = nickname;
		this.sign = sign;
		initialize();
	}

	private void initialize() {
		lb_mood = new JLabel();
		lb_mood.setVisible(true);
		lb_mood.setBounds(new Rectangle(51, 30, 231, 20));
		lb_mood.setFont(new Font("Dialog", Font.PLAIN, 12));
		lb_mood.setText(sign);
		lb_mood.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();
				lb_mood.setToolTipText(lb_mood.getText());
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();
			}

		});
		lb_nickName = new JLabel();
		lb_nickName.setBounds(new Rectangle(52, 10, 80, 20));
		lb_nickName.setFont(new Font("Dialog", Font.BOLD, 14));
		lb_nickName.setText(nickname);
		jPanel.setSize(new Dimension(300, 60));
		jPanel.setLayout(null);
		jPanel.add(getJButton(), null);
		jPanel.add(lb_nickName, null);
		jPanel.add(lb_mood, null);
		jPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();// ����Ƴ�ģ�������ı䱳����ɫ��
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();// ����ƽ�ģ�������ı䱳����ɫ��
			}
		});
	}
	
	public JPanel getjpanel(){
		return jPanel;
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
			jButton.setBounds(new Rectangle(8, 10, 40, 40));
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

class ListPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private int clickF = 0;
	private int clickB = 0;
	
	MainFrame mainframe;
	

	// 300*510


	public ListPane(int userID,String nickname,MainFrame mainframe) {
		super();
		this.mainframe=mainframe;
		initialize(userID,nickname);
		
	}

	private void initialize(int userID,String nickname) {
		

		JLabel[] friend = new JLabel[100];
		friend[0] = new JLabel();
		friend[0].setText("�ҵĺ���");
		friend[0].setIcon(new ImageIcon("././img/open.png"));
		friend[0].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(200, 408);
		this.setLocation(20, 5);
		this.add(friend[0], null);

		// �����û���Ϣ
		SocketThread clientSocket = new SocketThread();
		
		Message msg = new Message("friendlist");
		msg.setUid(userID);
		
		clientSocket.send(msg);
		Message friendlistResult = (Message)clientSocket.getResult();
		FriendsIfo[] friends = friendlistResult.friendlist;
		
		if (friends == null) {
			System.out.println("friends null !");
		} else {
			int i=1;
			while (friends[i-1]!=null) {
				friend[i] = new JLabel();
				friend[i].setIcon(new ImageIcon("././img/bg.png"));
				MemberModel friendDemo = new MemberModel(friends[i-1].img, friends[i-1].NickName,friends[i-1].Sign, 200);
				JPanel friendFrame = friendDemo.jPanel;
				friend[i].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
				friend[i].add(friendFrame);
				ToOneChatRoom chatFrame = new ToOneChatRoom(userID,nickname, friends[i-1],mainframe);
				friendDemo.getjpanel().addMouseListener(new MouseAdapter() {
					
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO �Զ����ɵķ������
						chatFrame.launch();
						System.out.println("mouse click succeed !");
					}
					
				});
				friend[i].setVisible(true);
				this.add(friend[i], null);
				
				System.out.println("friend 1");
				i+=1;
			}
		}

		friend[0].addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				clickF += 1;
				if (clickF % 2 == 1) {
					clickBlack2(friend);
					friend[0].setIcon(new ImageIcon("././img/close.png"));
					update();
				} else {
					clickBlack(friend);
					friend[0].setIcon(new ImageIcon("././img/open.png"));
					update();
				}
			}
		});
		
		addJLabel();
	}

	private void update() {// ����UI���棻
		this.updateUI();
	}

	private void clickBlack2(JLabel[] jb) {// �����ǩ��������ı�ǩȫ����Ϊ�����ӣ�
		int i=1;
		while (jb[i]!=null) {
			try {
				jb[i].setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			i+=1;
		}
		update();
	}

	private void clickBlack(JLabel[] jb) {// �����ǩ��������ı�ǩȫ����Ϊ���ӣ�
		int i=1;
		while (jb[i]!=null) {
			try {
				jb[i].setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			i+=1;

		}
		update();
	}

	private void addJLabel() {// ��Ӻ����������ݣ�
		final JLabel[] jb = new JLabel[4];
		jb[0] = new JLabel();
		jb[0].setText("������");
		jb[0].setIcon(new ImageIcon("././img/close.png"));
		jb[0].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		jb[0].addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				clickB += 1;
				if (clickB % 2 == 1) {
					clickBlack(jb);
					jb[0].setIcon(new ImageIcon("././img/open.png"));
				} else {
					clickBlack2(jb);
					jb[0].setIcon(new ImageIcon("././img/close.png"));
				}

			}
		});
		this.add(jb[0], null);
		for (int i = 1; i < jb.length-1; i++) {
			jb[i] = new JLabel();
			jb[i].setIcon(new ImageIcon("././img/bg.png"));
			jb[i].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			jb[i].add(new MemberModel(1, "CoolBabY" + (i + 3),"hello world!", 200).jPanel);
			jb[i].setVisible(false);
			this.add(jb[i], null);
		}

	}

}