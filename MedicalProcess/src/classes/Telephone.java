package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.medicalprocess.MainActivity;

public class Telephone {
	private int tid;
	private int indicatif;
	private int numero;
	
	public Telephone(ResultSet rs) throws SQLException{
		tid=rs.getInt("tid");
		indicatif=rs.getInt("indicatif");
		numero=rs.getInt("numero");
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getIndicatif() {
		return this.indicatif;
	}

	public void setIndicatif(int indicatif) {
		this.indicatif = indicatif;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String toString() {
		return "+"+indicatif+" "+numero;
	}
	
	public static Telephone getByTid(int tid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Telephones WHERE tid = '"+tid+"'");
		if(rs.next())
			return new Telephone(rs);
		return null;
	}
	
	public static List<Telephone> getListByPatient(int pid) throws SQLException
	{
		List<Telephone> result = new ArrayList<Telephone>();
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT T.tid AS tid FROM Telephones T, Telephones_Patients TP WHERE T.tid = TP.telephone AND TP.patient = '"+pid+"'");
		while(rs.next())
		{
			result.add(Telephone.getByTid(rs.getInt("tid")));
		}
		return result;
	}
}