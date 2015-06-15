package com.cardinalsolutions.countdowntimer;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import com.todddavies.components.progressbar.ProgressWheel;

/**
 * Countdown Timer
 *
 * CountdownTimerActivity is the main activity of this application.  To configure  is a utility class that allows the developer to turn off logging by setting the LOGGING
 * flag to false prior to generating an APK.
 *
 * @author Shane King
 *         28 Aug 2014
 *         10:00
 */
public class CountdownTimerActivity extends Activity {

	private static final String TAG = "CountdownTimer";

	private TextView mCountdownNote;
	private ProgressWheel mDaysWheel;
	private TextView mDaysLabel;
	private ProgressWheel mHoursWheel;
	private TextView mHoursLabel;
	private ProgressWheel mMinutesWheel;
	private TextView mMinutesLabel;
	private ProgressWheel mSecondsWheel;
	private TextView mSecondsLabel;

	// Timer setup
	Time conferenceTime = new Time(Time.getCurrentTimezone());
	int hour = 22;
	int minute = 33;
	int second = 0;
	int monthDay = 28;
	// month is zero based...7 == August
	int month = 7;
	int year;

	// Values displayed by the timer
	private int mDisplayDays;
	private int mDisplayHours;
	private int mDisplayMinutes;
	private int mDisplaySeconds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdown_timer);

		configureViews();
		configureConferenceDate();

	}

	private void configureViews() {
		
		this.conferenceTime.setToNow();
		this.year = conferenceTime.year;

		this.mCountdownNote = (TextView) findViewById(R.id.activity_countdown_timer_note);
		this.mDaysWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_days);
		this.mHoursWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_hours);
		this.mMinutesWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_minutes);
		this.mSecondsWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_seconds);
		this.mDaysLabel = (TextView) findViewById(R.id.activity_countdown_timer_days_text);
		this.mHoursLabel = (TextView) findViewById(R.id.activity_countdown_timer_hours_text);
		this.mMinutesLabel = (TextView) findViewById(R.id.activity_countdown_timer_minutes_text);
		this.mSecondsLabel = (TextView) findViewById(R.id.activity_countdown_timer_seconds_text);

	}

	private void configureConferenceDate() {
		conferenceTime.set(second, minute, hour, monthDay, month, year);
		conferenceTime.normalize(true);
		long confMillis = conferenceTime.toMillis(true);

		Time nowTime = new Time(Time.getCurrentTimezone());
		nowTime.setToNow();
		nowTime.normalize(true);
		long nowMillis = nowTime.toMillis(true);

		long milliDiff = confMillis - nowMillis;

		new CountDownTimer(milliDiff, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// decompose difference into days, hours, minutes and seconds
				CountdownTimerActivity.this.mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
				CountdownTimerActivity.this.mDisplayHours = (int) (((millisUntilFinished / 1000) - (CountdownTimerActivity.this.mDisplayDays * 86400)) / 3600);
				CountdownTimerActivity.this.mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((CountdownTimerActivity.this.mDisplayDays * 86400) + (CountdownTimerActivity.this.mDisplayHours * 3600))) / 60);
				CountdownTimerActivity.this.mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);

				CountdownTimerActivity.this.mDaysWheel.setText(String.valueOf(CountdownTimerActivity.this.mDisplayDays));
				CountdownTimerActivity.this.mDaysWheel.setProgress(CountdownTimerActivity.this.mDisplayDays);

				CountdownTimerActivity.this.mHoursWheel.setText(String.valueOf(CountdownTimerActivity.this.mDisplayHours));
				CountdownTimerActivity.this.mHoursWheel.setProgress(CountdownTimerActivity.this.mDisplayHours * 15);

				CountdownTimerActivity.this.mMinutesWheel.setText(String.valueOf(CountdownTimerActivity.this.mDisplayMinutes));
				CountdownTimerActivity.this.mMinutesWheel.setProgress(CountdownTimerActivity.this.mDisplayMinutes * 6);

				Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
				an.setFillAfter(true);

				CountdownTimerActivity.this.mSecondsWheel.setText(String.valueOf(CountdownTimerActivity.this.mDisplaySeconds));
				CountdownTimerActivity.this.mSecondsWheel.setProgress(CountdownTimerActivity.this.mDisplaySeconds * 6);
			}

			@Override
			public void onFinish() {
				//TODO: this is where you would launch a subsequent activity if you'd like.  I'm currently just setting the seconds to zero
				Logger.d(TAG, "Timer Finished...");
				CountdownTimerActivity.this.mSecondsWheel.setText("0");
	        		CountdownTimerActivity.this.mSecondsWheel.setProgress(0);
			}
		}.start();
	}
}
