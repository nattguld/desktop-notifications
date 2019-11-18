# desktop-notifications

## Dependencies
This repository uses the following dependencies:  
**audio:** For playing audio fragments. https://github.com/nattguld/audio  

## About
A simple desktop notification manager that displays a notification in the left bottom corner of the screen.
Once the ok button is pressed or the notification timed out it will be removed from the screen.
The notification shows partly transparent as long as there's no focus of the user.

![Alt text](https://i.imgur.com/Gtw2s5m.png "")

## Examples
The standard way of submitting a notification is the following:
```java
NotificationManager.submit(new DesktopNotification("Title", "Description", int displayTimeInSeconds);
NotificationManager.submit(new DesktopNotification("Title", "Description");
```

If you want to programatically control the display you can keep an instance to do so.
```java
DesktopNotification notif = new DesktopNotification("Title", "Description");
NotificationManager.submit(notif);
notif.setDisplayed(false);
```

You can also add audio to a notification.
```java
Audio audio = new Audio(URL url);
DesktopNotification notif = new DesktopNotification("Title", "Description", int optionalDisplayTimeInSeconds);
notif.setAudio(audio);
```

There is a default audio sound implemented too if you wish all your notifications to have a sound even if you didn't specify one.
Pass a flag to the notification submit in order to request the default audio.
```java
NotificationManager.submit(notif, true);
```

By default the notifications with a set display time will show a progress bar depleting based on the display time.
You can force turn this off with the following:
```java
new DesktopNotification("title", "description", time).setProgressHandler(null);
```

You can also override this behavior with your own implementation or add custom implementation for notifications with no set display time by implementing the progress handler interface.
```java
new DesktopNotification("title", "description").setProgressHandler(new IProgressBarHandler() {
  @Override
  public void onStart() {} //Executed before the progress bar starts updating
  @Override
  public void onFinish() {} //Executed once the progress bar finished updating
  @Override
  public int getProgressValue() {} //The current progress value (0-100)
  @Override
  public boolean isFinished() {} //Whether updating should be finished or not
  @Override
  public long getRefreshInterval() {} //The refresh interval on the GUI for the progress bar
  @Override
  public boolean isHideOnFinish() {} //Whether to hide the progress bar once finished or not
  @Override
  public boolean hasProgressText() {} //Whether the progress bar should show text or not
  @Override
  public String getProgressText() {} //The current progress text
});
```
