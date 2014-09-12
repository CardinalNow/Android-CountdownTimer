# Countdown Timer for Android

## Overview

This is an activity that performs a countdown to a specified target date/time.  In my usage, I developed an app that couldn't be viewed util a specific date/time.  So, the countdown timer was my launch activity for that app.  When the countdown expired I simply closed the countdown timer activiy and started the main application activity.

![Countdown Screen](https://github.com/CardinalNow/Android-CountdownTimer/blob/master/img/countdown.png)

## Usage

For this project, I utilized Todd Davies' [ProgressWheel](https://github.com/Todd-Davies/ProgressWheel) in combination with a standard Android CountdownTimer, to display a clock that counts down to a date/time of the developers choosing.  The target time objects (hour, minute, second, monthDay, month, year) are set in the CountdownTimerActivty class, and the countdown starts from now.  All of the colors are customizable in the resource XMLs.  For specifics on editing the ring colors, see the [ProgressWheel](https://github.com/Todd-Davies/ProgressWheel) project (although it's fairly intuitive if you check out the activity_countdown_timer.xml).

## Author / License

Copyright Cardinal Solutions 2013. Licensed under the MIT license.
<img src="https://raw.github.com/CardinalNow/NSURLConnection-Debug/master/logo_footer.png"/>
