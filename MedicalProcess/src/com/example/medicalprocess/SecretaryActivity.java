package com.example.medicalprocess;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SecretaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secretary);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secretary, menu);
		return true;
	}
	
	public void addNewPatient(View view) {
		Intent addNewPatientActivityIntent = new Intent(this, AddNewPatientActivity.class);
	    startActivity(addNewPatientActivityIntent);
	}
	
	public void addNewConsultation(View view) {
		Intent addNewConsultationActivityIntent = new Intent(this, AddNewConsultationActivity.class);
	    startActivity(addNewConsultationActivityIntent);
	}
	
	public void getConsultations(View view) {
		
	}

}
