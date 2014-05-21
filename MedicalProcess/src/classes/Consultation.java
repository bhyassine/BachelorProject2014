package classes;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultation {
	private int cid;
	private Utilisateur utilisateur;
	private Patient patient;
	private Date date;
	
	public Consultation(ResultSet rs) throws SQLException {
		cid = rs.getInt("cid");
		utilisateur = Utilisateur.getByUid(rs.getInt("utilisateur"));
		patient = Patient.getByPid(rs.getInt("patient"));
		date = rs.getDate("date");
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CodePrestation[] codesPrestations() {
		throw new UnsupportedOperationException();
	}
}