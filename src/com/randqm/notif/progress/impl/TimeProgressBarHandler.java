package com.randqm.notif.progress.impl;

import com.randqm.notif.progress.IProgressBarHandler;

/**
 * 
 * @author randqm
 *
 */

public class TimeProgressBarHandler implements IProgressBarHandler {
	
	/**
	 * The display time.
	 */
	private final long displayTime;
	
	/**
	 * The start time.
	 */
	private long startTime;
	
	
	/**
	 * Creates a new progress bar handler.
	 * 
	 * @param displayTime The display time.
	 */
	public TimeProgressBarHandler(long displayTime) {
		this.displayTime = displayTime;
	}
	
	@Override
	public void onStart() {
		this.startTime = System.currentTimeMillis();
	}
	
	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getProgressValue() {
		long elapsed = System.currentTimeMillis() - startTime;
		double progressRatio = (double)((double)elapsed / (double)displayTime);
		int intPct = (int)Math.round(progressRatio * 100);
		return 100 - intPct;
	}
	
	@Override
	public boolean isFinished() {
		return (startTime + displayTime) <= System.currentTimeMillis();
	}

	@Override
	public long getRefreshInterval() {
		long interval = displayTime / 1000;
		
		if (interval > 1000) {
			interval = 1000;
		}
		return interval;
	}

	@Override
	public boolean isHideOnFinish() {
		return false;
	}

	@Override
	public boolean hasProgressText() {
		return false;
	}

	@Override
	public String getProgressText() {
		return null;
	}

}
