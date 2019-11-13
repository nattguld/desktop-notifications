package com.randqm.notif;

import java.net.URL;

/**
 * 
 * @author randqm
 *
 */

public class DesktopNotification {
	
	/**
	 * The notification title.
	 */
	private final String title;
	
	/*
	 * The notification description.
	 */
	private final String description;
	
	/**
	 * The display time in seconds.
	 */
	private final int displayTime;
	
	/**
	 * The icon.
	 */
	@Deprecated
	private final String icon;
	
	/**
	 * The audio url.
	 */
	private URL audio;
	
	/**
	 * Holds since when the notification is being displayed.
	 */
	private long displayedSince;
	
	
	/**
	 * Creates a new desktop notification.
	 * 
	 * @param title The title.
	 * 
	 * @param description The description.
	 */
	public DesktopNotification(String title, String description) {
		this(title, description, -1);
	}
	
	/**
	 * Creates a new desktop notification.
	 * 
	 * @param title The title.
	 * 
	 * @param description The description.
	 * 
	 * @param displayTime The display time in seconds.
	 */
	public DesktopNotification(String title, String description, int displayTime) {
		this.title = title;
		this.description = description;
		this.displayTime = displayTime;
		this.displayedSince = 0L;
		this.icon = null;
	}
	
	/**
	 * Retrieves the title.
	 * 
	 * @return The title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Retrieves the description.
	 * 
	 * @return The description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Modifies the audio.
	 * 
	 * @param audio The new audio.
	 * 
	 * @return The notification.
	 */
	public DesktopNotification setAudio(URL audio) {
		this.audio = audio;
		return this;
	}
	
	/**
	 * Retrieves the audio.
	 * 
	 * @return The audio.
	 */
	public URL getAudio() {
		return audio;
	}
	
	/**
	 * Retrieves the display time in seconds.
	 * 
	 * @return The display time.
	 */
	public int getDisplayTime() {
		return displayTime;
	}
	
	/**
	 * Modifies whether the notification is being displayed or not.
	 * 
	 * @param displayed The new state.
	 * 
	 * @return The notification.
	 */
	public DesktopNotification setDisplayed(boolean displayed) {
		this.displayedSince = displayed ? System.currentTimeMillis() : 0L;
		return this;
	}
	
	/**
	 * Retrieves whether the notification is being displayed or not.
	 * 
	 * @return The result.
	 */
	public boolean isDisplayed() {
		return displayedSince != 0L;
	}
	
	/**
	 * Retrieves whether the desktop notification timed out or not.
	 * 
	 * @return The result.
	 */
	public boolean isTimedOut() {
		return !isPersistent() && ((System.currentTimeMillis() - displayedSince) > (displayTime * 1000));
	}
	
	/**
	 * Retrieves whether the notification is persistent or not.
	 * 
	 * @return The result.
	 */
	public boolean isPersistent() {
		return displayTime < 0;
	}

}