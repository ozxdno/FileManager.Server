package FileManager.Server;


// 001 - cannot connected to MySQL Server

public class Form {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//SQL.reconnect();
		//SQL.queryConfig("select * from " + SQL.table_config);
		//SQL.updata("insert into " + SQL.table_config + " values(\"test\",\"1\")");
		//SQL.disconnect();
		
		Server_TCP.start(0);
		
		//Server_UDP.restart(0);
		
		//Client_TCP.start();
	}

}
