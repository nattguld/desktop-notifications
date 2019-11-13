# desktop-notifications

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
DesktopNotification notif = new DesktopNotification("Title", "Description", int displayTimeInSeconds);
NotificationManager.submit(notif);
notif.setDisplayed(false);
```
