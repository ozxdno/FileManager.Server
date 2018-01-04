package FileManager.Server;


// 001 - cannot connected to MySQL Server

public class Form {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SQL.reconnect();
		SQL.getConfig("select * from " + SQL.table_config);
		
	}

}
