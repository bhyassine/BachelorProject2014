package com.example.medicalprocess;

import java.util.ArrayList;
import java.util.List;

import classes.Entite;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ShowEntitesAsyncTask extends AsyncTask<SignUpActivity, Void, List<Entite>> {
	SignUpActivity activity;
	@Override
	protected List<Entite> doInBackground(SignUpActivity... arg0) {
		// TODO Auto-generated method stub
		activity = arg0[0];
		return Entite.listAll();
	}
	
	protected void onPostExecute(List<Entite> entiteList)
	{
		activity.entites=entiteList;
		Spinner entite =(Spinner)activity.findViewById(R.id.entite_spinner);
		List<String> entiteStringList = new ArrayList<String>();
		entiteStringList.add("Choose your entity:");
		for(Entite e : entiteList)
		{
			entiteStringList.add(e.toString());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item, entiteStringList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		entite.setAdapter(adapter);
	}
}
