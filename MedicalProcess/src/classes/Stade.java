package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.medicalprocess.MainActivity;

public class Stade {
	private int sid;
	private Maladie maladie;
	private Phase phase;
	private int stade;

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Maladie getMaladie() {
		return this.maladie;
	}

	public void setMaladie(Maladie maladie) {
		this.maladie = maladie;
	}

	public Phase getPhase() {
		return this.phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public int getStade() {
		return this.stade;
	}

	public void setStade(int stade) {
		this.stade = stade;
	}

	public Stade(ResultSet rs) throws SQLException {
		sid = rs.getInt("sid");
		maladie = Maladie.getByMid(rs.getInt("maladie"));
		phase = Phase.getByPid(rs.getInt("phase"));
		stade = rs.getInt("stade");
	}

	public Stade[] autresAlternatives() {
		throw new UnsupportedOperationException();
	}

	public Stade stadeSuivant() throws SQLException {
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Stades WHERE mid = '"+maladie+"' AND stade = "+(stade+1));
		if(rs.next())
			return new Stade(rs);
		return null;
	}

	public Stade stadePrecedent() throws SQLException {
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Stades WHERE mid = '"+maladie+"' AND stade = "+(stade-1));
		if(rs.next())
			return new Stade(rs);
		return null;
	}
	
	public static Stade getBySid(int sid) throws SQLException
	{
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Stades WHERE sid = '"+sid+"'");
		if(rs.next())
			return new Stade(rs);
		return null;
	}
	
	public static List<Stade> getListByPatient(int pid) throws SQLException
	{
		List<Stade> result = new ArrayList<Stade>();
		Statement statement = MainActivity.connexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT S.sid AS sid FROM Stades S, Patients_Stades PS WHERE S.sid = PS.sid AND PS.pid = '"+pid+"'");
		while(rs.next())
		{
			result.add(Stade.getBySid(rs.getInt("sid")));
		}
		return result;	
	}
}