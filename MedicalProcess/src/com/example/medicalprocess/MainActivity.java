package com.example.medicalprocess;

import java.sql.Connection;

import db.BDDConnexion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	static public Connection connexion = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connexion = BDDConnexion.connect();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	///////// A modifier ulterieurement juste pour acceder à LoginActivity
	public void logIn(View view) {
	    Intent loginActivityIntent = new Intent(this, LoginActivity.class);
	    startActivity(loginActivityIntent);
	}
}
