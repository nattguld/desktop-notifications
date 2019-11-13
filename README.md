# desktop-notifications

## Dependencies
This repository uses the following dependencies:  
**audio:** For playing audio fragments. https://github.com/nattguld/audio  

## About
A simple desktop notification manager that displays a notification in the left bottom corner of the screen.
Once the ok button is pressed or the notification timed out it will be removed from the screen.

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
