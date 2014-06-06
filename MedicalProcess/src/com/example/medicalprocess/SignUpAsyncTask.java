package com.example.medicalprocess;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import classes.Utilisateur;
import android.os.AsyncTask;
import android.widget.Toast;

public class SignUpAsyncTask extends AsyncTask<Object, Void, Void> {
	SignUpActivity activity;
	String nom;
	String prenom;
	String email;
	String password;
	int entite;
	int fonction;
	@Override
	protected Void doInBackground(Object... params) {
		activity = (SignUpActivity) params[0];
		nom = (String) params[1];
		prenom = (String) params[2];
		email = (String) params[3];
		password = (String) params[4];
		entite = (Integer) params[5];
		fonction = (Integer) params[6];
		return null;
	}

	protected void onPostExecute(Void result)
	{
		try {
			Utilisateur.add(nom, prenom, password, email, entite, fonction);
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
