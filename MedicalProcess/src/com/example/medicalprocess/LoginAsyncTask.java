package com.example.medicalprocess;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import classes.UtilisateurConnecte;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginAsyncTask extends
		AsyncTask<Object, Void, Void> {
	LoginActivity activity;
	String email;
	String password;

	@Override
	protected Void doInBackground(Object... params) {
		activity = (LoginActivity) params[0];
		email = (String) params[1];
		password = (String) params[2];
		return null;
	}

	protected void onPostExecute(Void result)
	{
		try {
			UtilisateurConnecte user = UtilisateurConnecte.connect(email, password);
			MainActivity.user=user;
		} catch (NoSuchAlgorithmException e) {
			Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
			activity.init();
		} catch (SQLException e) {
			Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
			activity.init();
		}
		if (MainActivity.user!=null) {
			activity.finish();
			MainActivity.activity.comeBack();
		} else {
			Toast.makeText(activity, "Wrong password or username", Toast.LENGTH_LONG).show();
			activity.init();
		}
		
	}
}
