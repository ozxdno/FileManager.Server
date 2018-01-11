package FileManager.Server.OperateDB;

import java.sql.*;
import FileManager.Server.Global.*;

public class SQL {
	private String loginName;
	private String password;
	private String db_name;
	private String ipv4;
	private int port;
	private Connection connection;
	private boolean connected;
	private Statement query;
	
	public String getLoginName() {
		return loginName;
	}
	public String getPassword() {
		return password;
	}
	public String getDB_Name() {
		return db_name;
	}
	public String getIpv4() {
		return ipv4;
	}
	public int getPort() {
		return port;
	}
	public boolean isConnected() {
		return connected &&
			   connection != null &&
			   query != null;
	}
	
	public SQL() {
		clear();
	}
	protected void finalize() {
		disconnect();
	}
	
	public void clear() {
		loginName = "root";
		password = "ani1357658uiu";
		db_name = "filemanager";
		ipv4 = "127.0.0.1";
		port = 3306;
		connection = null;
		connected = false;
		query = null;
	}
	public void connect() {
		reconnect();
	}
	public void reconnect() {
		String url = "jdbc:mysql://" + ipv4 + ":" + String.valueOf(port) + "/" + db_name;
		connected = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, loginName, password);
		}catch(Exception e) {
			Global.CurrentError.show(16);
			connected = false;
			return;
		}
		try {
			query = connection.createStatement();
		}catch(Exception e) {
			Global.CurrentError.show(17);
			connected = false;
			return;
		}
	}
	public void disconnect() {
		if(query != null) {
			try {
				query.close();
			}catch(Exception e) {
				Global.CurrentError.show(18);
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
				connected = false;
			}catch(Exception e) {
				Global.CurrentError.show(19);
			}
		}
	}
	
	public ResultSet query(String where) {
		try {
			return query.executeQuery(where);
		}catch(Exception e) {
			Global.CurrentError.show(26);
			return null;
		}
	}
	public boolean updata(String statement) {
		try {
			query.executeUpdate(statement);
			return true;
		}catch(Exception e) {
			Global.CurrentError.show(27);
			return false;
		}
	}
	public boolean exist(String where) {
		return query(where) != null;
	}
}

