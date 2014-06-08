package com.example.medicalprocess;

import java.sql.Connection;
import java.sql.SQLException;

import classes.UtilisateurConnecte;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static MainActivity activity;
	
	static public Connection connexion = null;
	static public UtilisateurConnecte user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		new MainAsyncTask().execute();
		activity=this;
		if(user==null)
		{
		    Intent loginActivityIntent = new Intent(this, LoginActivity.class);
		    startActivity(loginActivityIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void comeBack() {
		if(user!=null)
			try {
				user.refresh();
			} catch (SQLException e) {
				Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		printInfo(user);
	}
	
	
	/**
	 * Affiche les informations de l'utilisateur
	 * @param user
	 */
	public void printInfo(UtilisateurConnecte user) {
		TextView name = (TextView)findViewById(R.id.main_name);
		TextView email = (TextView)findViewById(R.id.main_email);
		TextView valide = (TextView)findViewById(R.id.main_valide);
		TextView webmaster = (TextView)findViewById(R.id.main_webmaster);
		TextView fonction = (TextView)findViewById(R.id.main_fonction);
		TextView entite = (TextView)findViewById(R.id.main_entite);
		Button workButton = (Button)findViewById(R.id.main_work_button);
		name.setText(user.getNom()+" "+user.getPrenom());
		email.setText(user.getEmail());
		fonction.setText(user.getFonction().getNom());
		entite.setText(user.getEntite().toString());
		if(user.isValide()) {
			workButton.setVisibility(0);
		} else {
			valide.setText("Votre compte n'est pas encore valide, veuillez attendre la confirmation du webmaster");
		}
		if(user.isWebmaster())
			webmaster.setText("Vous êtes admin");
	}
	
	/**
	 * Gestion des scenarios possibles
	 * @param view
	 */
	public void workToDo(View view) {
		int fonctionNum = user.getFonction().getNumero();
		switch (fonctionNum) {
		
		// Medecin
		case 1:
			break;
		
		// Infirmier
		case 2:
			break;
		
		// Secretaire
		case 3:
			Intent secretaryActivityIntent = new Intent(this, SecretaryActivity.class);
		    startActivity(secretaryActivityIntent);
			break;
		
		// Laborantin/Pharmacien
		case 4:
			break;
		
		// Admin
		case 5:
			break;
		
		
		default:
			break;
		}
	}
	
	
	public void refresh(View view) {
	    comeBack();
	}
	
}
