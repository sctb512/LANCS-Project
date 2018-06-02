

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
//���հ棡����
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

		// ����Panel
		JPanel bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 400, 300);
		bgPanel.setOpaque(false);

		// ����ͼƬ����ӵ�����Panel����
		JLabel bgLabel = new JLabel(new ImageIcon("././img/bgImage.png"));
		bgPanel.add(bgLabel);

		// �����棬Ҳ���Ǳ��������һ��Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 400, 300);
		mainPanel.setLayout(null);

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

		// �û����ı���

		JTextField username = new JTextField();
		username.setBounds(144, 179, 138, 22);
		username.setBackground(null);
		username.setBorder(null);
		mainPanel.add(username);

		// �����ı���

		JPasswordField password = new JPasswordField();
		password.setBounds(144, 213, 138, 22);
		password.setBackground(null);
		password.setBorder(null);

		mainPanel.add(password);

		// �ı���ʾ��ǩ

		JLabel notice = new JLabel();
		notice.setBounds(119, 234, 163, 22);
		notice.setBackground(Color.BLACK);
		notice.setBorder(null);
		notice.setForeground(Color.RED);
		notice.setFont(new Font("����", Font.BOLD, 10));
		notice.setHorizontalAlignment(SwingConstants.CENTER);

		mainPanel.add(notice);

		JButton register = new JButton("ע��");
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

		JButton login = new JButton("��½");
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
					notice.setText("�˺Ż�����Ϊ�գ����������룡");
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
						notice.setText("�˺Ż�����������������룡");
					} else if (logResult.value == 2) {
						notice.setText("�˺Ų����ڣ����������룡");
					} else {
						System.out.println("ifo��" + logResult);
						notice.setText("���ִ�������ϵ����Ա��");
					}
				}
			}
		});

		mainPanel.add(login);
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
