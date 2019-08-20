# Notification Channels and Badges

App has been done by following the instructions detailed in the Google Codelab [Notification Channels and Badges (Java)][Java based Codelab] and [Notification Channels and Badges (Kotlin)][Kotlin based Codelab]. The original code by Google for this codelab can be referred here for [Java repository][] and [Kotlin repository][].

## What you will build

* Implement Notifications for an app that simulates Social media notifications.
* Create different Notification Channels for different types of Notifications.
* Show Notification Badges only for the particular/required type of Notification.

## Getting Started

* Android Studio 3.2 or higher
* Basic familiarity with Android-O Notifications.
* A device or virtual-device(emulator) that runs on Android-O with a launcher that supports Notification Badging.

### Branches in this Repository

* **[starter-code-java](https://github.com/kaushiknsanji/NotificationChannels/tree/starter-code-java)**
	* This is the Starter code for the [Java based Codelab][].
* **[solution-code-java](https://github.com/kaushiknsanji/NotificationChannels/tree/solution-code-java)**
	* This contains the Solution for the [Java based Codelab][].
	* In comparision with the original [Java Solution repository][], this repository additionally -
		* Uses `NotificationCompat`.
		* Safegaurds code-blocks dependent on versions.
		* Sets the Proirity on Notifications equivalent to its Importance, for devices running on versions less than Android-O. 
		* Fixes the issue of the Application not launching (on the second time) when a subsequent notification was clicked.
* **[starter-code-kotlin](https://github.com/kaushiknsanji/NotificationChannels/tree/starter-code-kotlin)**
	* This is the Starter code for the [Kotlin based Codelab][].
* **[solution-code-kotlin](https://github.com/kaushiknsanji/NotificationChannels/tree/solution-code-kotlin)**
	* This contains the Solution for the [Kotlin based Codelab][].
	* In comparision with the original [Kotlin Solution repository][], this repository additionally -
		* Uses `NotificationCompat`.
		* Safegaurds code-blocks dependent on versions.
		* Sets the Proirity on Notifications equivalent to its Importance, for devices running on versions less than Android-O.
		* Fixes the issue of the Application not launching (on the second time) when a subsequent notification was clicked.
		* Optimizes the usage of Kotlin.
* **[master](https://github.com/kaushiknsanji/NotificationChannels)**
	* Same as [solution-code-java](https://github.com/kaushiknsanji/NotificationChannels/tree/solution-code-java)
	* Contains this README.

## Sample Screenshot

<p align="center">
<img src="https://codelabs.developers.google.com/codelabs/notification-channels-kotlin/img/1af640335d9b64b3.png" width="40%"/>
</p>

<!-- Reference Style Links are to be placed after this -->
[Java based Codelab]: https://codelabs.developers.google.com/codelabs/notification-channels-java/index.html
[Java repository]: https://github.com/googlecodelabs/notification-channels-java
[Java Solution repository]: https://github.com/googlecodelabs/notification-channels-java/tree/Solution-4_Badging
[Kotlin based Codelab]: https://codelabs.developers.google.com/codelabs/notification-channels-kotlin/index.html
[Kotlin repository]: https://github.com/googlecodelabs/notification-channels-kotlin
[Kotlin Solution repository]: https://github.com/googlecodelabs/notification-channels-kotlin/tree/Solution-4_Badging

