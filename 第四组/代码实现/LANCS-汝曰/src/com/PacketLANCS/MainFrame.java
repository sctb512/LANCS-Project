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
	// 用于处理拖动事件，表示鼠标按下时的坐标，相对于JFrame
	int xOld = 0;
	int yOld = 0;

	public void Launch() {

		System.out.println(userID);
		
		//开启消息处理程序
		chatTcp.start();
		setSize(300, 560);
		setLayout(null);
		setLocation(920, 90);;
		;

		// 处理拖动事件
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

		// JLayeredPane用于添加两个图层的，一个用于背景，一个用于界面
		JLayeredPane layeredPane = getLayeredPane();
		// layeredPane.setBounds(0, 0, 400, 300);
		// add(layeredPane);

		// 背景Panel
		JPanel bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 300, 560);
		bgPanel.setOpaque(false);

		// 背景图片，添加到背景Panel里面
		JLabel bgLabel = new JLabel(new ImageIcon("././img/mainbgimg.png"));
		bgPanel.add(bgLabel);

		// 主界面，也就是背景上面的一层Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 300, 560);
		mainPanel.setLayout(null);

		// 最小化按钮
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

		// 关闭按钮
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

		// 用户头像

		JButton headImg = new JButton();
		headImg.setBounds(24, 46, 70, 70);
		headImg.setBackground(Color.GREEN);

		headImg.setIcon(new ImageIcon("././img/photoImg.png"));

		mainPanel.add(headImg);

		// 昵称

		JLabel nickName = new JLabel();
		nickName.setBounds(115, 50, 100, 30);
		nickName.setBackground(Color.GREEN);

		nickName.setFont(new Font("宋体", Font.BOLD, 22));
		nickName.setForeground(Color.WHITE);
		nickName.setText("爱上民谣的少年");

		mainPanel.add(nickName);

		// 个性签名

		JLabel sign = new JLabel();
		sign.setBounds(115, 94, 160, 20);
		sign.setBackground(Color.GREEN);

		sign.setFont(new Font("宋体", Font.BOLD, 10));
		sign.setForeground(Color.WHITE);
		sign.setText("City of stars, Are you shining just for me?");

		mainPanel.add(sign);

		// 接收用户信息
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
		//转移消息
		
		FriendIfoFrame headButton = new FriendIfoFrame(ifo);
		headImg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				headButton.display();
			}
		});
		
		// 好友列表

		JButton friendList = new JButton();
		friendList.setBounds(0, 140, 100, 32);
	
		friendList.setContentAreaFilled(false);
		friendList.setIcon(new ImageIcon("././img/friends.png"));

		mainPanel.add(friendList);

		// 列表

		JPanel list = new JPanel();
		list.setBounds(0, 172, 300, 360);
		

		JScrollPane scrollPane = new JScrollPane(new ListPane(userID,nickname,this));
		scrollPane.setBackground(Color.ORANGE);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// 不显示水平滚动条
		list.setLayout(new BorderLayout());
		list.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(list);

		// 搜索文本框

		JTextField searchText = new JTextField();
		searchText.setBounds(0, 532, 244, 28);
	
		searchText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchText.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				searchText.setText("输入用户名或账号，点击添加好友！");
			}
		});

		mainPanel.add(searchText);

		// 搜索按钮

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
					//notice.setText("账号或密码错误，请重新输入！");
				} else {
					System.out.println("出现错误，请联系管理员！");
					//notice.setText("出现错误，请联系管理员！");
				}
				
			}
		});
		mainPanel.add(searchButton);

		// 聊天室

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

		setUndecorated(true);// 隐藏边框
		setVisible(true);

	}

	private void miniTray() { // 窗口最小化到任务栏托盘

		ImageIcon trayImg = new ImageIcon("././img/leida.png");// 托盘图标

		PopupMenu pop = new PopupMenu(); // 增加托盘右击菜单
		MenuItem show = new MenuItem("还原");
		MenuItem exit = new MenuItem("退出");

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // 按下还原键

				tray.remove(trayIcon);
				setVisible(true);
				setExtendedState(JFrame.NORMAL);
				toFront();
			}

		});

		exit.addActionListener(new ActionListener() { // 按下退出键

			public void actionPerformed(ActionEvent e) {

				tray.remove(trayIcon);
				System.exit(0);

			}

		});

		pop.add(show);
		pop.add(exit);

		trayIcon = new TrayIcon(trayImg.getImage(), "汝曰局域网通信系统", pop);
		trayIcon.setImageAutoSize(true);

		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // 鼠标器双击事件

				if (e.getClickCount() == 2) {

					tray.remove(trayIcon); // 移去托盘图标
					setVisible(true);
					setExtendedState(JFrame.NORMAL); // 还原窗口
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

	public JButton jButton = null;// 显示好友头像；

	public JPanel jPanel = new JPanel();// 模板容器；

	private JLabel lb_nickName = null;// 显示昵称；

	private int pic;

	private String nickname = null;
	
	private String sign = null;

	private JLabel lb_mood = null;// 显示心情；

	public MemberModel(int pic, String nickname,String sign, int len) {
		super();
		this.pic = pic;// 头像编（有多种方法可以实现，这种最简单）
		this.nickname = nickname;// 昵称；
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
				exchangeExited();// 鼠标移出模板区，改变背景颜色；
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();// 鼠标移进模板区，改变背景颜色；
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
					exchangeExited();// 鼠标移出模板区，改变背景颜色；
				}

				public void mouseEntered(java.awt.event.MouseEvent e) {
					exchangeEnter();// 鼠标移进模板区，改变背景颜色；
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
		friend[0].setText("我的好友");
		friend[0].setIcon(new ImageIcon("././img/open.png"));
		friend[0].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(200, 408);
		this.setLocation(20, 5);
		this.add(friend[0], null);

		// 接收用户信息
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
						// TODO 自动生成的方法存根
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

	private void update() {// 更新UI界面；
		this.updateUI();
	}

	private void clickBlack2(JLabel[] jb) {// 点击标签，将后面的标签全部设为不可视；
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

	private void clickBlack(JLabel[] jb) {// 点击标签，将后面的标签全部设为可视；
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

	private void addJLabel() {// 添加黑名单的内容；
		final JLabel[] jb = new JLabel[4];
		jb[0] = new JLabel();
		jb[0].setText("黑名单");
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