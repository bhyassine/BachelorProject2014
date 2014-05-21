package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.medicalprocess.MainActivity;

public class Maladie {
	private int mid;
	private String nom;
	
	public Maladie(ResultSet rs) throws SQLException {
		mid = rs.getInt("mid");
		nom = rs.getString("nom");
	}

	public int getMid() {
		return this.mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public static Maladie getByMid(int mid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Maladies WHERE mid = '"+mid+"'");
		if(rs.next())
			return new Maladie(rs);
		return null;
	}
}