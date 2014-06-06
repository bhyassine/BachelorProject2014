package com.example.medicalprocess;

import java.sql.Connection;

import db.BDDConnexion;

import android.os.AsyncTask;

public class MainAsyncTask extends
		AsyncTask<Void, Void, Connection> {
	MainActivity activity;

	@Override
	protected Connection doInBackground(Void... params) {
		return BDDConnexion.connect();
	}

	protected void onPostExecute(Connection connection) {
		MainActivity.connexion=connection;
	}
}
