package classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.medicalprocess.MainActivity;

import db.BDDConnexion;

public class Nature {
	private int numero;
	private String nom;
	
	public Nature(ResultSet rs) throws SQLException {
		numero=rs.getInt("numeroNature");
		nom=rs.getString("nom");
	}
	
	public static Nature getByNumero(int numero)
	{
		Connection conn = BDDConnexion.connect();
		Statement statement;
		ResultSet rs=null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("SELECT * FROM Dico_Nature WHERE numeroNature = "+numero);
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return new Nature(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Nature> listAll()
	{
		List<Nature> list = new ArrayList<Nature>();
		Statement statement;
		ResultSet rs;
		try {
			statement = MainActivity.connexion.createStatement();
			rs = statement.executeQuery("SELECT * FROM Dico_Nature ORDER BY numeroNature");
			while(rs.next())
				list.add(new Nature(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}