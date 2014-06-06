package com.example.medicalprocess;

import java.util.List;

import classes.Entite;
import classes.Fonction;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends Activity {

	List<Fonction> fonctions;
	List<Entite> entites;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		if(MainActivity.user!=null)
		{
		    finish();
		}
		new ShowFonctionsAsyncTask().execute(this);
		new ShowEntitesAsyncTask().execute(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	public void createNewAccount(View view)
	{
		EditText email = (EditText)findViewById(R.id.emailSignUp_edit_text);
		EditText password = (EditText)findViewById(R.id.passwordSignUp_edit_text);
		EditText firstName = (EditText)findViewById(R.id.firstName_edit_text);
		EditText lastName = (EditText)findViewById(R.id.lastName_edit_text);
		Spinner fonction =(Spinner)findViewById(R.id.fonction_spinner);
		Spinner entite = (Spinner)findViewById(R.id.entite_spinner);
		String emailString = email.getText().toString();
		String passwordString = password.getText().toString();
		String firstNameString = firstName.getText().toString();
		String lastNameString = lastName.getText().toString();
		int fonctionInt = fonctions.get(fonction.getSelectedItemPosition()-1).getNumero();
		int entiteInt = entites.get(entite.getSelectedItemPosition()-1).getEid();
		new SignUpAsyncTask().execute(this, lastNameString, firstNameString, emailString, passwordString, entiteInt, fonctionInt);
	}
	
	public void init()
	{
		EditText email = (EditText)findViewById(R.id.emailSignUp_edit_text);
		EditText password = (EditText)findViewById(R.id.passwordSignUp_edit_text);
		EditText firstName = (EditText)findViewById(R.id.firstName_edit_text);
		EditText lastName = (EditText)findViewById(R.id.lastName_edit_text);
		Spinner fonction =(Spinner)findViewById(R.id.fonction_spinner);
		Spinner entite = (Spinner)findViewById(R.id.entite_spinner);
		email.setText("");
		password.setText("");
		firstName.setText("");
		lastName.setText("");
		fonction.setSelection(0);
		entite.setSelection(0);
	}
	
}
