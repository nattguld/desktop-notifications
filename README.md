# desktop-notifications

A simple desktop notification manager.
Displays a notification in the left bottom corner of the screen.

//Example usage, display time being an optional parameter.
NotificationManager.submit(new DesktopNotification("Title", "Description", int displayTimeInSeconds);

Once display time elapsed or the OK button on the notification is pressed the notification will fade away.
