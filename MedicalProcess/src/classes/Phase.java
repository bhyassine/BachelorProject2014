package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.medicalprocess.MainActivity;

public class Phase {
	private int pid;
	private String nom;
	
	public Phase(ResultSet rs) throws SQLException {
		pid = rs.getInt("pid");
		nom = rs.getString("nom");
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
	
	public static Phase getByPid(int pid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Phases WHERE pid = '"+pid+"'");
		if(rs.next())
			return new Phase(rs);
		return null;
	}
}