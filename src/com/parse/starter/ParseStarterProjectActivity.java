package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseStarterProjectActivity extends Activity {

	TextView loginInfo;
	TextView signupInfo;
	Button offerButton;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		loginInfo = (TextView) findViewById(R.id.LoginInfo);
		signupInfo = (TextView) findViewById(R.id.SignupInfo);
		offerButton = (Button) findViewById(R.id.OfferButton);

		// create parseUser object for signup
		ParseUser user = new ParseUser();
		user.setUsername("Khedri");
		user.setPassword("Khedri");
		user.setEmail("khedri@khedri.com");

		// get location and input it here
		ParseGeoPoint point = new ParseGeoPoint(40.0, -30.0);

		// other fields can be set just like with ParseObject
		user.put("location", point);
		user.put("offering", false);

		user.saveInBackground();

		// signup
		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					// Hooray! Let them use the app now.
					signupInfo.setText("Successfully signed up.");
					Log.d("signup", "Success");
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
					signupInfo.setText("Signup failed: "
							+ e.getLocalizedMessage());
					Log.d("signup", "Failed");
				}
			}
		});

		// login
		ParseUser.logInInBackground("Khedri", "Khedri", new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if (user != null) {
					// Hooray! The user is logged in.
					loginInfo.setText("Successfully logged in as: "
							+ user.getUsername());
					user.put("offering", false);
					user.saveInBackground();
					Log.d("login", "Success");
				} else {
					// Login failed. Look at the ParseException to see what
					// happened.
					loginInfo.setText("Failed to login.");
					Log.d("login", "Failed");
				}
			}
		});

		// offer ride button
		offerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ParseStarterProjectActivity.this,
						FiltersActivity.class);
				startActivity(intent);

			}

		});

		ParseAnalytics.trackAppOpened(getIntent());
	}
}
