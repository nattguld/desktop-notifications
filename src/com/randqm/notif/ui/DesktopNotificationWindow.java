package com.randqm.notif.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;

import com.randqm.notif.DesktopNotification;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author randqm
 *
 */

@SuppressWarnings("serial")
public class DesktopNotificationWindow extends JWindow {
	
	/**
	 * The default width.
	 */
	private static final int DEFAULT_WIDTH = 300;
	
	/**
	 * The default height.
	 */
	private static final int DEFAULT_HEIGHT = 120;
	
	/**
	 * The default focus opacity.
	 */
	private static final float DEFAULT_FOCUS_OPACITY = 1f;
	
	/**
	 * The default unfocused opacity.
	 */
	private static final float DEFAULT_UNFOCUSED_OPACITY = 0.9f;
	
	/**
	 * The desktop notification.
	 */
	private final DesktopNotification notif;
	
	/**
	 * Whether the window is being hovered or not.
	 */
	private boolean hovered;
	
	
	/**
	 * Creates a new desktop notfication window.
	 * 
	 * @param notif The notification.
	 */
	public DesktopNotificationWindow(DesktopNotification notif) {
		this.notif = notif;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int bottomSpaceTaken = scnMax.bottom;
		int leftSpaceTaken = scnMax.left;
		
		setAlwaysOnTop(true);
		setOpacity(DEFAULT_UNFOCUSED_OPACITY);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocation(leftSpaceTaken + 10, (int)(screenSize.getHeight() - DEFAULT_HEIGHT - 10 - bottomSpaceTaken));
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setOpacity(DEFAULT_FOCUS_OPACITY);
				hovered = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setOpacity(DEFAULT_UNFOCUSED_OPACITY);
				hovered = false;
			}
		};
		addMouseListener(mouseAdapter);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(mouseAdapter);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(mouseAdapter);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		okButton.setBounds(190, 84, 100, 25);
		panel.add(okButton);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.addMouseListener(mouseAdapter);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		titleLabel.setBounds(10, 11, 280, 14);
		panel.add(titleLabel);
		
		JTextArea txtrAlo = new JTextArea();
		txtrAlo.addMouseListener(mouseAdapter);
		txtrAlo.setEditable(false);
		txtrAlo.setText("alo");
		txtrAlo.setBackground(titleLabel.getBackground());
		txtrAlo.setForeground(titleLabel.getForeground());
		txtrAlo.setBorder(titleLabel.getBorder());
		txtrAlo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrAlo.setWrapStyleWord(true);
		txtrAlo.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(txtrAlo);
		scroll.addMouseListener(mouseAdapter);
		scroll.setBounds(10, 36, 280, 37);
		panel.add(scroll);
	}
	
	@Override
	public void dispose() {
		notif.setDisplayed(false);
		
		onDispose();
		
		super.dispose();
	}
	
	/**
	 * Handles custom operations when the notification gets disposed.
	 */
	protected void onDispose() {
		//To override when required
	}
	
	/**
	 * Retrieves whether the window is being hovered or not.
	 * 
	 * @return The result.
	 */
	public boolean isHovered() {
		return hovered;
	}
	
	/**
	 * Retrieves the desktop notification.
	 * 
	 * @return The notification.
	 */
	public DesktopNotification getNotification() {
		return notif;
	}
	
}