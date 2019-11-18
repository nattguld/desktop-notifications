package com.randqm.notif.progress;

/**
 * 
 * @author randqm
 *
 */

public interface IProgressBarHandler {

	
	/**
	 * Retrieves the current progress value.
	 * 
	 * @return The value.
	 */
	public int getProgressValue();
	
	/**
	 * Retrieves whether progress text is used or not.
	 * 
	 * @return The result.
	 */
	public boolean hasProgressText();
	
	/**
	 * Retrieves the progress text.
	 * 
	 * @return The progress text.
	 */
	public String getProgressText();
	
	/**
	 * Handles methods when the handler starts.
	 */
	public void onStart();
	
	/**
	 * Handles when the progress finished.
	 */
	public void onFinish();
	
	/**
	 * Retrieves whether the handler is finished or not.
	 * 
	 * @return The result.
	 */
	public boolean isFinished();
	
	/**
	 * Whether to hide the progress bar when finished or not.
	 * 
	 * @return The result.
	 */
	public boolean isHideOnFinish();
	
	/**
	 * Retrieves the refresh interval in milliseconds.
	 * 
	 * @return Retrieves the refresh interval.
	 */
	public long getRefreshInterval();

}
