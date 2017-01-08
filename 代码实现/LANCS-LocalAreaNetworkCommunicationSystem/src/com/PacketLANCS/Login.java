package com.PacketLANCS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Login {
	public static void main(String args[]) {
		new LoginFrame().Launch();
	}
}

@SuppressWarnings("serial")
class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
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

		// JLayeredPane用于添加两个图层的，一个用于背景，一个用于界面
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 400, 300);
		add(layeredPane);

		// 背景Panel
		JPanel bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 400, 300);

		layeredPane.add(bgPanel);

		// 背景图片，添加到背景Panel里面
		JLabel bgLabel = new JLabel(new ImageIcon("././img/bgImage.png"));
		bgPanel.add(bgLabel);

		// 主界面，也就是背景上面的一层Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 400, 300);
		mainPanel.setLayout(null);
		layeredPane.add(mainPanel);

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
			public void mousePressed(MouseEvent e){ 
				System.exit(0);
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
			public void mousePressed(MouseEvent e){ 
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
		username.setBounds(144, 189, 138, 22);
		username.setBackground(null);
		username.setBorder(null);
		mainPanel.add(username);

		// 密码文本框

		JTextField password = new JTextField();
		password.setBounds(144, 223, 138, 22);
		password.setBackground(null);
		password.setBorder(null);

		mainPanel.add(password);

		JButton register = new JButton("注册");
		register.setBounds(119, 256, 80, 25);
		register.setForeground(Color.WHITE);
		register.setBackground(Color.BLACK);
		register.setBorder(null);

		mainPanel.add(register);

		JButton login = new JButton("登陆");
		login.setBounds(202, 256, 80, 25);
		login.setForeground(Color.WHITE);
		login.setBackground(Color.BLACK);
		login.setBorder(null);

		mainPanel.add(login);

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
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

	}

}

// private Point offset;
// private Component host;
//
// public void Launch() {
//
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// setSize(400, 247);
// setLocationRelativeTo(null);
// setUndecorated(true);//隐藏边框
// getRootPane().setBorder(
// BorderFactory.createBevelBorder(BevelBorder.RAISED));
// install(this);
//
// JPanel title = new JPanel();
// getContentPane().add(title);
//
// JButton exit = new JButton();
// title.add(exit);
//
//
// setVisible(true);
// }
//
// public synchronized void install(Component comp) {
// uninstall();
// host = comp;
// host.addMouseListener(new MouseListener());
// host.addMouseMotionListener(new MouseListener());
// }
//
// public synchronized void uninstall() {
// if (host != null) {
// host.removeMouseListener(new MouseListener());
// host.removeMouseMotionListener(new MouseListener());
// host = null;
// }
// }
//
// class MouseListener extends MouseAdapter {
// public void mousePressed(MouseEvent e) {
// if (e.getSource() == host)
// offset = e.getPoint();
// }
//
// public void mouseDragged(MouseEvent e) {
// if (e.getSource() != host)
// return;
// final int x = host.getX();
// final int y = host.getY();
// final Point lastAt = e.getPoint();
// host.setLocation(x + lastAt.x - offset.x, y + lastAt.y - offset.y);
// }
// }

// }
