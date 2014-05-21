package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodePrestation {
	private String code;
	private String description;

	public CodePrestation(ResultSet rs) throws SQLException {
		code = rs.getString("code");
		description = rs.getString("description");
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}