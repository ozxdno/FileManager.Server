package FileManager.Server.Collection;

import java.util.*;
import java.sql.*;
import FileManager.Server.Model.*;
import FileManager.Server.OperateDB.*;
import FileManager.Server.Global.*;

public class Configs {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Set<ConfigModel> configs;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Set<ConfigModel> getConfigs() {
		return configs;
	}
	public ConfigModel getConfig(String field) {
		Iterator<ConfigModel> it = configs.iterator();
		while(it.hasNext()) {
			ConfigModel c = it.next();
			if(c.getField().equals(field)) {
				it.remove();
				return c;
			}
		}
		return null;
	}
	public ConfigModel getConfig(ResultSet set) {
		try {
			ConfigModel c = new ConfigModel();
			c.setField(set.getString("Field"));
			c.setValue(set.getString("Value"));
			return c;
		} catch(Exception e) {
			Global.CurrentError.show(28);
			return null;
		}
	}
	public ConfigModel getConfig_NotRemove(String field) {
		Iterator<ConfigModel> it = configs.iterator();
		while(it.hasNext()) {
			ConfigModel c = it.next();
			if(c.getField().equals(field)) {
				return c;
			}
		}
		return null;
	}
	public ConfigModel getNext() {
	    Iterator<ConfigModel> it = configs.iterator();
		if(it.hasNext()) {
			ConfigModel c = it.next();
			it.remove();
			return c;
		}
		return null;
	}
	public String getSqlTableCols() {
		String cols = 
				"FIELD VARCHAR(100) NOT NULL, "
				+ "`VALUE` VARCHAR(1024) NOT NULL";
		return cols;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setSqlDefaultRows(SQL sql) {
		boolean ok = sql.updata("INSERT INTO CONFIGS VALUES('NextIndex_File','0');");
		if(!ok) { return ok; }
		ok = sql.updata("INSERT INTO CONFIGS VALUES('NextIndex_User','0');");
		if(!ok) { return ok; }
		ok = sql.updata("INSERT INTO CONFIGS VALUES('Rootpath','DFsadAS');");
		return ok;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Configs() {
		clear();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		if(configs == null) {
			configs = new HashSet<ConfigModel>();
		}
		configs.clear();
	}
	public boolean load() {
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			return false;
		}
		ResultSet set = sql.query("SELECT * FROM Configs;");
		if(set == null) {
			String s = "CREATE TABLE Configs(" + getSqlTableCols() + ");";
			if(sql.updata(s)) {
				return setSqlDefaultRows(sql);
			}
			return false;
		}
		try {
			while(set.next()) {
				ConfigModel c = getConfig(set);
				if(c != null) {
					configs.add(c);
				}
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(28);
			return false;
		}
	}
	public boolean exists(String field) {
		return getConfig(field) != null;
	}
	public boolean addConfig(ConfigModel config) {
		return configs.add(config);
	}
	public boolean isEmpty() {
		return configs.isEmpty();
	}
	public int size() {
		return configs.size();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
