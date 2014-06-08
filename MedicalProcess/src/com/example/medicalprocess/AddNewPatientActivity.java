package com.example.medicalprocess;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import classes.Patient;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewPatientActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_patient, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * Creer un nouveau patient lorsqu'on appuie sur le bouton Create
	 * @param view
	 */
	public void createNewPatient(View view) {
		EditText firstName = (EditText)findViewById(R.id.patientFirstName_edit_text);
		EditText lastName = (EditText)findViewById(R.id.patientLastName_edit_text);
		EditText email = (EditText)findViewById(R.id.patientEmail_edit_text);
		EditText age = (EditText)findViewById(R.id.patientAge_edit_text);
		EditText gender = (EditText)findViewById(R.id.patientSexe_edit_text);
		
		String firstNameString = firstName.getText().toString();
		String lastNameString = lastName.getText().toString();
		String emailString = email.getText().toString();
		String ageString = age.getText().toString();
		String genderString = gender.getText().toString();
		
		new AddNewPatientAsyncTask().execute(this,lastNameString,firstNameString,emailString,ageString,genderString);

	}
	
	public void init() {
		EditText firstName = (EditText)findViewById(R.id.patientFirstName_edit_text);
		EditText lastName = (EditText)findViewById(R.id.patientLastName_edit_text);
		EditText email = (EditText)findViewById(R.id.patientEmail_edit_text);
		EditText age = (EditText)findViewById(R.id.patientAge_edit_text);
		EditText gender = (EditText)findViewById(R.id.patientSexe_edit_text);
		
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		age.setText("");
		gender.setText("");
	}

	
	public class AddNewPatientAsyncTask extends AsyncTask<Object, Void, Void> {
		AddNewPatientActivity activity;
		String nom;
		String prenom;
		String email;
		int age;
		char sexe;
		@Override
		protected Void doInBackground(Object... params) {
			activity = (AddNewPatientActivity) params[0];
			nom = (String) params[1];
			prenom = (String) params[2];
			email = (String) params[3];
			age = Integer.parseInt((String) params[4]);
			sexe = ((String) params[5]).charAt(0);
			return null;
		}

		protected void onPostExecute(Void result) {
			try {
				Patient.add(nom, prenom, email, age, sexe);
				activity.finish();
			} catch (NoSuchAlgorithmException e) {
				Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
				activity.init();
			} catch (SQLException e) {
				Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
				activity.init();
			}
		}
	}

}
