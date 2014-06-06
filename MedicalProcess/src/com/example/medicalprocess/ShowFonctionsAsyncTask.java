package com.example.medicalprocess;

import java.util.ArrayList;
import java.util.List;

import classes.Fonction;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ShowFonctionsAsyncTask extends AsyncTask<SignUpActivity, Void, List<Fonction>> {
	SignUpActivity activity;
	@Override
	protected List<Fonction> doInBackground(SignUpActivity... arg0) {
		// TODO Auto-generated method stub
		activity = arg0[0];
		return Fonction.listAll();
	}
	
	protected void onPostExecute(List<Fonction> fonctionList)
	{
		activity.fonctions=fonctionList;
		Spinner fonction =(Spinner)activity.findViewById(R.id.fonction_spinner);
		List<String> fonctionStringList = new ArrayList<String>();
		fonctionStringList.add("Choose your function:");
		for(Fonction f : fonctionList)
		{
			fonctionStringList.add(f.getNom());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item, fonctionStringList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fonction.setAdapter(adapter);
	}

}
