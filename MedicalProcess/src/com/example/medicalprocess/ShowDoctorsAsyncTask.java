package com.example.medicalprocess;

import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import classes.Fonction;
import classes.Utilisateur;


public class ShowDoctorsAsyncTask extends AsyncTask<AddNewConsultationActivity, Void, List<Utilisateur>> {
	AddNewConsultationActivity activity;
	@Override
	protected List<Utilisateur> doInBackground(AddNewConsultationActivity... arg0) {
		// TODO Auto-generated method stub
		activity = arg0[0];
		////// Il faut changer l'entite en fonction de l'appel
		return Utilisateur.listAllPerFonctionPerEntite(Fonction.getByNumero(1), MainActivity.user.getEntite());
	}
	
	protected void onPostExecute(List<Utilisateur> doctorPerEntiteList) {
		activity.doctors=doctorPerEntiteList;
		Spinner entite =(Spinner)activity.findViewById(R.id.doctorNewConsultation_spinner);
		List<String> doctorsStringList = new ArrayList<String>();
		doctorsStringList.add("Choose the doctor for the consultation:");
		for(Utilisateur d : doctorPerEntiteList)
		{
			doctorsStringList.add(d.toString());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item, doctorsStringList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		entite.setAdapter(adapter);
	}
}

