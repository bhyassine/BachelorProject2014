package classes;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.Functions;

import com.example.medicalprocess.MainActivity;

public class Utilisateur {
	private int uid;
	private String nom;
	private String prenom;
	private String password;
	private String email;
	private Entite entite;
	private Fonction fonction;
	private boolean valide;
	private boolean webmaster;
	
	public Utilisateur(ResultSet rs) throws SQLException
	{
		uid=rs.getInt("uid");
		nom=rs.getString("nom");
		prenom=rs.getString("prenom");
		password=rs.getString("password");
		email=rs.getString("email");
		entite=Entite.getByEid(rs.getInt("numeroEntite"));
		fonction=Fonction.getByNumero(rs.getInt("numeroFonction"));
		if(rs.getInt("valide")==1)
			valide=true;
		else
			valide=false;
		if(rs.getInt("webmaster")==1)
			webmaster=true;
		else
			webmaster=false;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	public boolean isWebmaster() {
		return webmaster;
	}

	public void setWebmaster(boolean webmaster) {
		this.webmaster = webmaster;
	}

	public static Utilisateur add(String nom, String prenom, String password,
			String email, int entite, int fonction) throws SQLException, NoSuchAlgorithmException {
		Statement statement = MainActivity.connexion.createStatement();
		statement.executeUpdate("INSERT INTO Utilisateurs SET nom = '"+nom+"' , prenom = '"+prenom+"' , email = '"+email+"' , password = '"+Functions.hash(password)+"' , numeroEntite = "+entite+" , numeroFonction = "+fonction+" , valide=0");
		return getByEmail(email);
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
	
	public static Utilisateur getByUid(int uid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Utilisateurs WHERE uid = '"+uid+"'");
		if(rs.next())
			return new Utilisateur(rs);
		return null;
	}
	
	public static Utilisateur getByEmail(String email) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Utilisateurs WHERE email = '"+email+"'");
		if(rs.next())
			return new Utilisateur(rs);
		return null;
	}
	
	public void refresh() throws SQLException
	{
		Utilisateur user = Utilisateur.getByUid(uid);
		uid=user.getUid();
		nom=user.getNom();
		prenom=user.getPrenom();
		password=user.getPassword();
		email=user.getEmail();
		entite=user.getEntite();
		fonction=user.getFonction();
		valide=user.isValide();
		webmaster=user.isWebmaster();
	}
	
	public static List<Utilisateur> listAllPerFonctionPerEntite(Fonction fonction,Entite entite) {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		Statement statement;
		ResultSet rs;
		try {
			statement = MainActivity.connexion.createStatement();
			int fonctionNum = fonction.getNumero();
			int entiteNum = entite.getNature().getNumero();
			rs = statement.executeQuery("SELECT * FROM Utilisateurs WHERE numeroEntite='"+entiteNum+"' AND numeroFonction='"+fonctionNum+"'");
			while(rs.next())
				list.add(new Utilisateur(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public String toString() {
		return nom+" "+prenom+" "+uid;
	}
	
	
	
	
}