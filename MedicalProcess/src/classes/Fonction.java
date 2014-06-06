package classes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.medicalprocess.MainActivity;

public class Fonction {
	private int numero;
	private String nom;
	
	public Fonction(ResultSet rs) throws SQLException {
		numero=rs.getInt("numeroFonction");
		nom=rs.getString("nom");
	}
	
	public static Fonction getByNumero(int numero)
	{
		Statement statement;
		ResultSet rs=null;
		try {
			statement = MainActivity.connexion.createStatement();
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
	
	public static List<Fonction> listAll()
	{
		List<Fonction> list = new ArrayList<Fonction>();
		Statement statement;
		ResultSet rs;
		try {
			statement = MainActivity.connexion.createStatement();
			rs = statement.executeQuery("SELECT * FROM Dico_Fonction ORDER BY numeroFonction");
			while(rs.next())
				list.add(new Fonction(rs));
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