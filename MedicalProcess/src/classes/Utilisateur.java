package classes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.medicalprocess.MainActivity;

public final class Utilisateur {
	private int uid;
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private String email;
	private Entite entite;
	private Fonction fonction;
	
	private Utilisateur(ResultSet rs) throws SQLException
	{
		uid=rs.getInt("uid");
		nom=rs.getString("nom");
		prenom=rs.getString("prenom");
		username=rs.getString("username");
		password=rs.getString("password");
		email=rs.getString("email");
		entite=Entite.getByEid(rs.getInt("entite"));
		fonction=Fonction.getByNumero(rs.getInt("fonction"));
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Entite getEntite() {
		return this.entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public Fonction getFonction() {
		return this.fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	
	public static Utilisateur connect(String username, String password) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Utilisateurs WHERE username = '"+username+"' AND password = '"+password+"'");
		if(rs.next())
			return new Utilisateur(rs);
		return null;
	}
	
	public static Utilisateur getByUid(int uid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Utilisateurs WHERE uid = '"+uid+"'");
		if(rs.next())
			return new Utilisateur(rs);
		return null;
	}
	
}