package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.medicalprocess.MainActivity;

public class Adresse {
	private int aid;
	private String rue;
	private int num;
	private int cp;
	private String ville;
	private String pays;
	
	public Adresse(ResultSet rs) throws SQLException {
		aid = rs.getInt("aid");
		rue = rs.getString("rue");
		num = rs.getInt("num");
		cp = rs.getInt("cp");
		ville = rs.getString("ville");
		pays = rs.getString("pays");
	}

	public int getAid() {
		return this.aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCp() {
		return this.cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String toString() {
		return rue+", "+num+"\n"+ville+" "+cp+"\n"+pays;
	}
	
	public static Adresse getByAid(int aid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Adresses WHERE aid = '"+aid+"'");
		if(rs.next())
			return new Adresse(rs);
		return null;
	}
	
	public static List<Adresse> getListByPatient(int pid) throws SQLException
	{
		List<Adresse> result = new ArrayList<Adresse>();
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT A.aid AS aid FROM Adresses A, Adressees_Patients AP WHERE A.aid = AP.telephone AND AP.patient = '"+pid+"'");
		while(rs.next())
		{
			result.add(Adresse.getByAid(rs.getInt("aid")));
		}
		return result;
	}
}