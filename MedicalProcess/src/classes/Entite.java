package classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.medicalprocess.MainActivity;

import db.BDDConnexion;

public class Entite {
	private int eid;
	private String nom;
	private Nature nature;
	
	public Entite(ResultSet rs) throws SQLException {
		eid=rs.getInt("eid");
		nom=rs.getString("nom");
		nature=Nature.getByNumero(rs.getInt("numeroNature"));
	}

	public static Entite getByEid(int eid)
	{
		Connection conn = BDDConnexion.connect();
		Statement statement;
		ResultSet rs=null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("SELECT * FROM Entites WHERE eid = "+eid);
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return new Entite(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Entite> listAll()
	{
		List<Entite> list = new ArrayList<Entite>();
		Statement statement;
		ResultSet rs;
		try {
			statement = MainActivity.connexion.createStatement();
			rs = statement.executeQuery("SELECT * FROM Entites ORDER BY eid");
			while(rs.next())
				list.add(new Entite(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int getEid() {
		return this.eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Nature getNature() {
		return this.nature;
	}
	
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	public String toString()
	{
		return nom+" - "+nature.getNom();
	}

}