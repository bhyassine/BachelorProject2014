package classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.BDDConnexion;

public class Fonction {
	private int numero;
	private String nom;
	
	public Fonction(ResultSet rs) throws SQLException {
		numero=rs.getInt("numeroFonction");
		nom=rs.getString("nom");
	}
	
	public static Fonction getByNumero(int numero)
	{
		Connection conn = BDDConnexion.connect();
		Statement statement;
		ResultSet rs=null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("SELECT * FROM Dico_Fonction WHERE numeroFonction = "+numero);
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return new Fonction(rs);
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