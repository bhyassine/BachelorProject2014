package com.example.medicalprocess;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import classes.Consultation;
import classes.Utilisateur;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNewConsultationActivity extends Activity {

	List<Utilisateur> doctors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_consultation);
		new ShowDoctorsAsyncTask().execute(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_consultation, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void addNewConsultation(View view) {
		EditText patient = (EditText)findViewById(R.id.patientId_edit_text);
		Spinner doctorConsultation = (Spinner)findViewById(R.id.doctorNewConsultation_spinner);
		DatePicker dateConsultation = (DatePicker)findViewById(R.id.dateNewConsultation_datePicker);

		int patientId = Integer.parseInt(patient.getText().toString());
		int doctorId = doctors.get(doctorConsultation.getSelectedItemPosition()-1).getUid();
		@SuppressWarnings("deprecation")
		Date dateCons = new Date(dateConsultation.getYear(), dateConsultation.getMonth(), dateConsultation.getDayOfMonth());
	
		new AddNewConsultationAsyncTask().execute(this,patientId,doctorId,dateCons);
	}
	
	public void init() {
		EditText patient = (EditText)findViewById(R.id.patientId_edit_text);
		Spinner doctorConsultation = (Spinner)findViewById(R.id.doctorNewConsultation_spinner);
		DatePicker dateConsultation = (DatePicker)findViewById(R.id.dateNewConsultation_datePicker);
		
		patient.setText("");
		doctorConsultation.setSelection(0);
		//// Revoir la mise à jour de la date
		dateConsultation.updateDate(0, 0, 0);
	}
	
	
	public class AddNewConsultationAsyncTask extends AsyncTask<Object, Void, Void> {
		AddNewConsultationActivity activity;
		int patientId;
		int doctorId;
		Date dateConsultation;
		
		@Override
		protected Void doInBackground(Object... params) {
			activity = (AddNewConsultationActivity) params[0];
			patientId = (Integer) params[1];
			doctorId = (Integer) params[2];
			dateConsultation = (Date) params[3];
			return null;
		}

		protected void onPostExecute(Void result) {
			try {
				Consultation.add(patientId, doctorId, dateConsultation);
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
