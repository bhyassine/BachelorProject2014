package classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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