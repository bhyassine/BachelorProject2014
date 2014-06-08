package classes;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import com.example.medicalprocess.MainActivity;

public class Patient {
	private int pid;
	private String nom;
	private String prenom;
	private String email;
	private int age;
	private char sexe;
	
	public Patient(ResultSet rs) throws SQLException{
		pid = rs.getInt("pid");
		nom = rs.getString("nom");
		prenom = rs.getString("prenom");
		email = rs.getString("email");
		age = rs.getInt("age");
		sexe = rs.getString("sexe").charAt(0);
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSexe() {
		return this.sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public List<Telephone> getTelephones() throws SQLException {
		return Telephone.getListByPatient(pid);
	}

	public List<Adresse> getAdresses() throws SQLException {
		return Adresse.getListByPatient(pid);
	}

	public List<Stade> getStades() throws SQLException {
		return Stade.getListByPatient(pid);
	}
	
	public List<Consultation> consultations() {
		throw new UnsupportedOperationException();
	}

	public List<Utilisateur> utilisateurs() {
		throw new UnsupportedOperationException();
	}
	
	public static Patient getByPid(int pid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Patients WHERE uid = '"+pid+"'");
		if(rs.next())
			return new Patient(rs);
		return null;
	}
	
	
	public static void add(String nom, String prenom, String email, int age, 
			char sexe) throws SQLException, NoSuchAlgorithmException {
		Statement statement = MainActivity.connexion.createStatement();
		statement.executeUpdate("INSERT INTO Patients SET nom = '"+nom+"' , prenom = '"+prenom+"' , email = '"+email+"' , age = '"+age+"' , sexe = '"+sexe+"'");
		
	}
	
	
}