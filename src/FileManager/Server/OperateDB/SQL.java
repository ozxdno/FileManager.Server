package FileManager.Server.OperateDB;

import java.sql.*;

public class SQL {
	public String loginName = "root";
	public String password = "ani1357658uiu";
	public String db_name = "filemanager";
	public String ipv4 = "127.0.0.1";
	public int port = 3306;
	public String table_config = "config";
	public String table_files = "files";
	private Connection connection = null;
	private Statement query = null;
	
	public void clear() {
		loginName = "";
		password = "";
		ipv4 = "";
		port = -1;
		connection = null;
		query = null;
	}
	public void connect() {
		reconnect();
	}
	public void reconnect() {
		String url = "jdbc:mysql://" + ipv4 + ":" + String.valueOf(port) + "/" + db_name;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, loginName, password);
		}catch(Exception e) {
			
			return;
		}
		try {
			query = connection.createStatement();
		}catch(Exception e) {
			
			return;
		}
	}
	public void disconnect() {
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
	
	public void init() {
		
	}
	public ResultSet query(String where) {
		try {
			return query.executeQuery(where);
		}catch(Exception e) {
			return null;
		}
	}
	public boolean updata(String statement) {
		try {
			query.executeUpdate(statement);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean exist(String where) {
		return query(where) != null;
	}
}

