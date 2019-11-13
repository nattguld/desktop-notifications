package com.randqm.notif;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import com.randqm.audio.Audio;
import com.randqm.audio.AudioManager;
import com.randqm.notif.ui.DesktopNotificationWindow;

/**
 * 
 * @author randqm
 *
 */

public class DesktopNotificationManager {
	
	/**
	 * The default notification audio.
	 */
	private static final URL DEFAULT_AUDIO = DesktopNotificationManager.class.getResource("/res/audio/default_notification.wav");
	
	/**
	 * The currently displayed desktop notification windows.
	 */
	private static List<DesktopNotificationWindow> displayed = new CopyOnWriteArrayList<>();
	
	/**
	 * Whether notifications are disabled or not.
	 */
	private static boolean disabled;
	
	
	/**
	 * Submits a new desktop notification.
	 * 
	 * @param notif The notification.
	 */
	public static void submit(DesktopNotification notif) {
		submit(notif, false);
	}
	
	/**
	 * Submits a new desktop notification.
	 * 
	 * @param notif The notification.
	 * 
	 * @param defaultAudio Whether to play the default audio if none is specified.
	 */
	public static void submit(DesktopNotification notif, boolean defaultAudio) {
		if (disabled) {
			return;
		}
		DesktopNotificationWindow notifWindow = new DesktopNotificationWindow(notif);
		notifWindow.setVisible(true);
		
		notif.setDisplayed(true);
		displayed.add(notifWindow);
		
		if (Objects.isNull(notif.getAudio()) && defaultAudio) {
			notif.setAudio(DEFAULT_AUDIO);
		}
		if (Objects.nonNull(notif.getAudio())) {
			AudioManager.submit(new Audio(notif.getAudio()));
		}
		if (displayed.size() == 1) {
			handleDisplay();
		}
	}
	
	/**
	 * Handles the display of desktop notifications.
	 */
	private static void handleDisplay() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!displayed.isEmpty()) {
					for (DesktopNotificationWindow notifWindow : displayed) {
						if (!notifWindow.getNotification().isDisplayed()) {
							displayed.remove(notifWindow);
							continue;
						}
						if (notifWindow.getNotification().isTimedOut()) {
							if (notifWindow.isHovered()) {
								continue;
							}
							notifWindow.dispose();
							displayed.remove(notifWindow);
							continue;
						}
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	/**
	 * Disables the notifications.
	 */
	public static void disabled() {
		disabled = true;
	}
	
	/**
	 * Enabled the notifications.
	 */
	public static void enable() {
		disabled = false;
	}
	
	/**
	 * Clears all the notifications.
	 */
	public static void clearAllNotifications() {
		for (DesktopNotificationWindow notifWindow : displayed) {
			notifWindow.dispose();
		}
		displayed.clear();
	}

}
