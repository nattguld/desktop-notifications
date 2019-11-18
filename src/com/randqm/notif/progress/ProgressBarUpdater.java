package com.randqm.notif.progress;

import java.util.Objects;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 * 
 * @author randqm
 *
 */

public class ProgressBarUpdater {
	
	
	/**
	 * Submits a handler for a given progress bar.
	 * 
	 * @param progressBar The progress bar.
	 * 
	 * @param handler The handler.
	 */
	public static void submitHandler(JProgressBar progressBar, IProgressBarHandler handler) {
		if (handler.hasProgressText()) {
			progressBar.setStringPainted(true);
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				toggleVisibility(progressBar, true);
				handler.onStart();
				
				while (!handler.isFinished()) {
					updateValue(progressBar, handler.getProgressValue());
					
					if (handler.hasProgressText()) {
						updateText(progressBar, handler.getProgressText());
					}
					try {
						Thread.sleep(handler.getRefreshInterval());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.onFinish();
				
				if (handler.isHideOnFinish()) {
					toggleVisibility(progressBar, false);
				}
			}
		}).start();
	}
	
	/**
	 * Updates the value of a progress bar.
	 * 
	 * @param progressBar The progress bar.
	 * 
	 * @param value The value.
	 */
	private static void updateValue(JProgressBar progressBar, int value) {
		if (value < 0 || value > 100) {
			System.err.println("Invalid progress bar value");
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setValue(value);
			}
		});
	}
	
	/**
	 * Updates the text of a progress bar.
	 * 
	 * @param progressBar The progress bar.
	 * 
	 * @param text The text.
	 */
	private static void updateText(JProgressBar progressBar, String text) {
		if (Objects.isNull(text)) {
			System.err.println("No progress text passed");
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setString(text);
			}
		});
	}
	
	/**
	 * Toggles the visibility of a progress bar.
	 * 
	 * @param progressBar The progress bar.
	 * 
	 * @param visible Whether to make the progress bar visible or not.
	 */
	private static void toggleVisibility(JProgressBar progressBar, boolean visible) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setVisible(visible);
			}
		});
	}

}