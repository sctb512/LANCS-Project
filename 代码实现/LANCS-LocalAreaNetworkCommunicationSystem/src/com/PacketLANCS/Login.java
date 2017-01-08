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
	// ���ڴ����϶��¼�����ʾ��갴��ʱ�����꣬�����JFrame
	int xOld = 0;
	int yOld = 0;

	public void Launch() {

		setSize(400, 300);
		setLayout(null);
		setLocationRelativeTo(null);

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
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 400, 300);
		add(layeredPane);

		// ����Panel
		JPanel bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 400, 300);

		layeredPane.add(bgPanel);

		// ����ͼƬ����ӵ�����Panel����
		JLabel bgLabel = new JLabel(new ImageIcon("././img/bgImage.png"));
		bgPanel.add(bgLabel);

		// �����棬Ҳ���Ǳ��������һ��Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 400, 300);
		mainPanel.setLayout(null);
		layeredPane.add(mainPanel);

		// ��С����ť
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

		// �رհ�ť
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

		// �û����ı���

		JTextField username = new JTextField();
		username.setBounds(144, 189, 138, 22);
		username.setBackground(null);
		username.setBorder(null);
		mainPanel.add(username);

		// �����ı���

		JTextField password = new JTextField();
		password.setBounds(144, 223, 138, 22);
		password.setBackground(null);
		password.setBorder(null);

		mainPanel.add(password);

		JButton register = new JButton("ע��");
		register.setBounds(119, 256, 80, 25);
		register.setForeground(Color.WHITE);
		register.setBackground(Color.BLACK);
		register.setBorder(null);

		mainPanel.add(register);

		JButton login = new JButton("��½");
		login.setBounds(202, 256, 80, 25);
		login.setForeground(Color.WHITE);
		login.setBackground(Color.BLACK);
		login.setBorder(null);

		mainPanel.add(login);

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
			// TODO �Զ����ɵ� catch ��
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
// setUndecorated(true);//���ر߿�
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
