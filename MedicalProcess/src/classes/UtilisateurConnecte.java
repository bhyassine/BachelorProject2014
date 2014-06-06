package classes;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Functions;

import com.example.medicalprocess.MainActivity;

public final class UtilisateurConnecte extends Utilisateur {

	private UtilisateurConnecte(ResultSet rs) throws SQLException
	{
		super(rs);
	}

	public static UtilisateurConnecte connect(String email, String password) throws SQLException, NoSuchAlgorithmException
	{
		password = Functions.hash(password);
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Utilisateurs WHERE email = '"+email+"' AND password = '"+password+"'");
		if(rs.next())
			return new UtilisateurConnecte(rs);
		return null;
	}
}
