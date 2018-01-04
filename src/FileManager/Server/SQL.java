package FileManager.Server;

import java.util.*;
import java.sql.*;

public class SQL {
	public static String loginName = "root";
	public static String password = "ani1357658uiu";
	public static String ipv4 = "127.0.0.1";
	public static int port = 3306;
	public static String table_config = "config";
	public static Connection config = null;
	public static Statement query_config = null;
	
	public static String getUrl(String table) {
		return "jdbc:mysql://" + ipv4 + ":" + String.valueOf(port) + "/" + table;
	}
	
	public static void clear() {
		loginName = "";
		password = "";
		ipv4 = "";
		port = -1;
		config = null;
	}
	public static void reconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = getUrl(table_config);
			config = DriverManager.getConnection(getUrl(table_config), loginName, password);
		}catch(Exception e) {
			Server.setErrorCode(0);
			return;
		}
		try {
			query_config = config.createStatement();
		}catch(Exception e) {
			Server.setErrorCode(0);
			return;
		}
	}
	
	public static ArrayList<ConfigModel> getConfig(String where) {
		ArrayList<ConfigModel> configs = new ArrayList<ConfigModel>();
		if(config == null) {
			return configs;
		}
		try {
			if(config.isClosed()) {
				return configs;
			}
		}catch(Exception e) {
			return configs;
		}
		
		ResultSet set;
		try {
			set = query_config.executeQuery(where);
		}catch(Exception e) {
			Server.setErrorCode(0);
			return configs;
		}
		try {
			while(set.next()) {
				String field = set.getString("field");
				String value = set.getString("value");
				configs.add(new ConfigModel(field,value));
			}
		}catch(Exception e) {
			Server.setErrorCode(0);
			return configs;
		}
		
		return configs;
	}
}
