package FileManager.Server.Collection;

import java.util.*;
import FileManager.Server.Model.*;
import FileManager.Server.OperateDB.SQL;

import java.sql.*;
import FileManager.Server.Global.*;

public class Supports {
	private Map<String,SupportModel> supports;
	
	public Map<String,SupportModel> getSupports() {
		return supports;
	}
	public SupportModel getSupport(String extension) {
		return supports.get(extension);
	}
	public SupportModel getSupport(ResultSet set) {
		try {
			SupportModel s = new SupportModel();
			s.setExtension(set.getString("Extension"));
			s.setType(set.getInt("Type"));
			s.setShowExtension(set.getString("Show"));
			s.setHideExtension(set.getString("Hide"));
			return s;
		} catch(Exception e) {
			Global.CurrentError.show(33);
			return null;
		}
	}
	public String getSqlTableCols() {
		return "`Extension` VARCHAR(100) NOT NULL, " +
				"`Type` INT NOT NULL, " +
				"`Show` VARCHAR(100) NOT NULL, " +
				"`Hide` VARCHAR(100) NOT NULL";
	}
	
	public Supports() {
		clear();
	}
	
	public void clear() {
		if(supports == null) {
			supports = new HashMap<String,SupportModel>();
		}
		supports.clear();
	}
	public boolean load() {
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			return false;
		}
		ResultSet set = sql.query("SELECT * FROM Supports;");
		if(set == null) {
			String s = "CREATE TABLE Supports(" + getSqlTableCols() + ");";
			return sql.updata(s);
		}
		try {
			while(set.next()) {
				SupportModel s = getSupport(set);
				supports.put(s.getExtension(), s);
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(33);
			return false;
		}
	}
}
