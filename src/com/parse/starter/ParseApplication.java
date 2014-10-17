package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		
		// Add your initialization code here
		Parse.initialize(this, "C4Tdp39qRWTfACzKVwFzdMG6BQeeQOQgLtPLXKB3",
				"ltQsE4fiDwwC6BNQfo7GbRWkvHKBF6YcqJwQ0OIg");

		ParseInstallation.getCurrentInstallation().saveInBackground();

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}
}
