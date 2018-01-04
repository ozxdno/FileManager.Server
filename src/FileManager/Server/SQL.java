package FileManager.Server;

import java.util.*;
import java.sql.*;

public class SQL {
	public static String loginName = "root";
	public static String password = "ani1357658uiu";
	public static String db_name = "filemanager";
	public static String ipv4 = "127.0.0.1";
	public static int port = 3306;
	public static String table_config = "config";
	public static String table_files = "files";
	private static Connection connection = null;
	private static Statement query = null;
	
	public static void clear() {
		loginName = "";
		password = "";
		ipv4 = "";
		port = -1;
		connection = null;
		query = null;
	}
	public static void reconnect() {
		String url = "jdbc:mysql://" + ipv4 + ":" + String.valueOf(port) + "/" + db_name;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, loginName, password);
		}catch(Exception e) {
			ErrorCode.setError(0);
			return;
		}
		try {
			query = connection.createStatement();
		}catch(Exception e) {
			ErrorCode.setError(0);
			return;
		}
	}
	public static void disconnect() {
		if(query != null) {
			try {
				query.close();
			}catch(Exception e) {
				;
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			}catch(Exception e) {
				;
			}
		}
	}
	
	public static ArrayList<ConfigModel> queryConfig(String where) {
		ArrayList<ConfigModel> configs = new ArrayList<ConfigModel>();
		if(connection == null) {
			return configs;
		}
		try {
			if(connection.isClosed()) {
				return configs;
			}
		}catch(Exception e) {
			return configs;
		}
		
		ResultSet set;
		try {
			set = query.executeQuery(where);
		}catch(Exception e) {
			ErrorCode.setError(0);
			return configs;
		}
		try {
			while(set.next()) {
				String field = set.getString("field");
				String value = set.getString("value");
				configs.add(new ConfigModel(field,value));
			}
		}catch(Exception e) {
			ErrorCode.setError(0);
			return configs;
		}
		
		return configs;
	}
	
	public static boolean updata(String statement) {
		try {
			query.executeUpdate(statement);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
