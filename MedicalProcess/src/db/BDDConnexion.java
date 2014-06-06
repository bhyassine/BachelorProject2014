package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDDConnexion
{
	private static Connection connexion = null;
	private BDDConnexion()
	{
		
	}
	public static Connection connect() {
		if(connexion!=null)
			return connexion;
        try {
            String userName = "root";
            String password = "root";
            String url = "jdbc:mysql://10.0.2.2:8889/MedicalProcess";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connexion = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
        }
        return connexion;
    }
}
