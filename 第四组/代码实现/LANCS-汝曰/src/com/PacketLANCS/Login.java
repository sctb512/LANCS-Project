

package com.PacketLANCS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//最终版！保留
public class Login {
	public static void main(String args[]) {		
		
		System.out.println("look!");		
		new LoginFrame().Launch();
	}
}

class LoginFrame extends JFrame {
	SocketThread clientSocket;
	public LoginFrame(){

	}
	private static final long serialVersionUID = 1L;
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
	String userName, passWord;
	// 用于处理拖动事件，表示鼠标按下时的坐标，相对于JFrame
	int xOld = 0;
	int yOld = 0;

	public void Launch() {

		setSize(400, 300);
		setLayout(null);
		setLocationRelativeTo(null);

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

		// 背景Panel
		JPanel bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 400, 300);
		bgPanel.setOpaque(false);

		// 背景图片，添加到背景Panel里面
		JLabel bgLabel = new JLabel(new ImageIcon("././img/bgImage.png"));
		bgPanel.add(bgLabel);

		// 主界面，也就是背景上面的一层Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 400, 300);
		mainPanel.setLayout(null);

		// 最小化按钮
		JButton minButton = new JButton();
		minButton.setContentAreaFilled(false);
		minButton.setBorder(null);
		minButton.setIcon(new ImageIcon("././img/minIcon.png"));
		minButton.setBounds(360, 0, 20, 20);
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
		closeButton.setBounds(380, 0, 20, 20);
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

		// 用户名文本框

		JTextField username = new JTextField();
		username.setBounds(144, 179, 138, 22);
		username.setBackground(null);
		username.setBorder(null);
		mainPanel.add(username);

		// 密码文本框

		JPasswordField password = new JPasswordField();
		password.setBounds(144, 213, 138, 22);
		password.setBackground(null);
		password.setBorder(null);

		mainPanel.add(password);

		// 文本提示标签

		JLabel notice = new JLabel();
		notice.setBounds(119, 234, 163, 22);
		notice.setBackground(Color.BLACK);
		notice.setBorder(null);
		notice.setForeground(Color.RED);
		notice.setFont(new Font("宋体", Font.BOLD, 10));
		notice.setHorizontalAlignment(SwingConstants.CENTER);

		mainPanel.add(notice);

		JButton register = new JButton("注册");
		register.setBounds(119, 256, 80, 25);
		register.setForeground(Color.WHITE);
		register.setBackground(Color.BLACK);
		register.setBorder(null);
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Register().Launch();
			}
		});

		mainPanel.add(register);

		JButton login = new JButton("登陆");
		login.setBounds(202, 256, 80, 25);
		login.setForeground(Color.WHITE);
		login.setBackground(Color.BLACK);
		login.setBorder(null);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userName = username.getText();
				passWord = String.valueOf(password.getPassword());
				SocketThread clientSocket = new SocketThread();
				
				System.out.println("password :" + passWord);
				if (userName.length() < 1 || passWord.length() < 1) {
					notice.setText("账号或密码为空，请重新输入！");
				} else {
					Message msg = new Message("log");
					msg.setIfo(userName, passWord);

					clientSocket.send(msg);
					
					Message logResult;
					logResult = (Message) clientSocket.getResult();

					if (logResult.value == 1) {
						System.out.println("login succeed");
						new MainFrame(logResult.Uid).Launch();							
						setVisible(false);
					} else if (logResult.value == 0) {
						notice.setText("账号或密码错误，请重新输入！");
					} else if (logResult.value == 2) {
						notice.setText("账号不存在，请重新输入！");
					} else {
						System.out.println("ifo：" + logResult);
						notice.setText("出现错误，请联系管理员！");
					}
				}
			}
		});

		mainPanel.add(login);
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
