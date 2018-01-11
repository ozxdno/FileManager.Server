package FileManager.Server.Communication;

import java.net.*;
import java.io.*;
import java.util.*;
import FileManager.Server.Model.*;
import FileManager.Server.Global.*;

public class Server_TCP {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final int maxport = 65535;
	private final int minport = 10000;
	private int port = maxport;
	private Accept accept;
	private Set<Client> clients = new HashSet<Client>();
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getPort() {
		return port;
	}
	public Set<Client> getClients(){
		return clients;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setPort(int port) {
		if(port < minport) {
			return false;
		}
		this.port = port;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Server_TCP() {
		clear();
	}
	public Server_TCP(int port) {
		clear();
		setPort(port);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		this.port = maxport;
		accept = null;
		if(clients == null) {
			clients = new HashSet<Client>();
		}
		clients.clear();
	}
	public boolean start() {
		return restart();
	}
	public boolean restart() {
		stop();
		try {
			accept = new Accept(new ServerSocket(this.port));
			accept.start();
			return true;
		}catch(Exception e) {
			Global.CurrentError.show(34);
			return false;
		}
	}
	public void stop() {
		if(accept != null) {
			accept.myAbort();
		}
	}
	public void addClient(Client client) {
		clients.add(client);
	}
	public void removeClient(Client client) {
		clients.remove(client);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}






class Accept extends Thread {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private ServerSocket socket0;
	private Client client;
	private boolean abort;
	private boolean running;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Accept(ServerSocket server) {
		super.setName("TCP_Server");
		socket0 = server;
		abort = false;
		running = false;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void run() {
		abort = false;
		running = true;
		if(socket0 == null) {
			running = false;
			return;
		}
		while(!abort && !socket0.isClosed()) {
			try {
				client = new Client(socket0.accept());
				Global.Server_TCP.addClient(client);
				client.start();
			}catch(Exception e) {
				;
			}
		}
		running = false;
	}
	public void myAbort() {
		try {
			if(socket0 != null) {
				socket0.close();
			}
		}catch(Exception e) {
			Global.CurrentError.show(35);
		}
		while(running) {
			abort = true;
		}
		try {
			if(socket0 != null) {
				socket0.close();
			}
		}catch(Exception e) {
			;
		}
	}
	public boolean isRunning() {
		return running;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}






class Client extends Thread {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Socket client;
	private boolean abort;
	private boolean running;
	private BufferedReader br;
	private String accept;
	private PrintWriter pw;
	private String send;
	private Server_CMD cmd;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public UserModel getUser() {
		return cmd.getUser();
	}
	public ConfigModel getCommand() {
		return cmd.getCommand();
	}
	public String getAccept() {
		return accept;
	}
	public String getSend() {
		return send;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setClient(Socket client) {
		if(client == null || client.isClosed()) {
			return false;
		}
		this.client = client;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Client(Socket client) {
		clear();
		setClient(client);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		super.setName("TCP_Client");
		client = null;
		abort = false;
		running = false;
		br = null;
		accept = "";
		pw = null;
		send = "";
		cmd = new Server_CMD();
	}
	public void run() {
		abort = false;
		running = true;
		if(client == null) {
			running = false;
			return;
		}
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		}catch(Exception e) {
			Global.CurrentError.show(36);
			return;
		}
		
		while(!abort && !client.isClosed() && accept != null) {
			try {
				accept = br.readLine();
				cmd.setCommand(accept);
				send = cmd.deal();
				pw.println(send);
                pw.flush();
			}catch(Exception e) {
				Global.CurrentError.show(37);
			}
		}
		running = false;
		Global.Server_TCP.removeClient(this);
	}
	public void myAbort() {
		try {
			client.close();
		} catch(Exception e) {
			Global.CurrentError.show(38);
		}
		
		while(running) {
			abort = true;
		}
		
		try {
			client.close();
		} catch(Exception e) {
			;
		}
	}
	public boolean isRunning() {
		return running;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
