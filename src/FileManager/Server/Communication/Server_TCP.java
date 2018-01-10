package FileManager.Server.Communication;

import java.net.*;
import java.io.*;
import java.util.*;
import FileManager.Server.Model.*;
import FileManager.Server.Global.*;

public class Server_TCP {
	private int maxport = 65535;
	private int minport = 10000;
	private int port = maxport;
	private Accept accept;
	private Set<Client> clients = new HashSet<Client>();

	public int getPort() {
		return port;
	}
	public Set<Client> getClients(){
		return clients;
	}
	
	public boolean setPort(int port) {
		if(port < minport) {
			return false;
		}
		this.port = port;
		return true;
	}
	
	public void clear() {
		this.port = maxport;
		accept = null;
		if(clients == null) {
			clients = new HashSet<Client>();
		}
	}
	public boolean start(int port) {
		return restart(port);
	}
	public boolean restart(int port) {
		stop();
		clear();
		setPort(port);
		try {
			accept = new Accept(new ServerSocket(this.port));
			accept.start();
			return true;
		}catch(Exception e) {
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
}

class Accept extends Thread {
	private ServerSocket socket0;
	private Client client;
	private boolean abort;
	private boolean running;
	
	public Accept(ServerSocket server) {
		socket0 = server;
		abort = false;
		running = false;
	}
	
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
}

class Client extends Thread {
	private Socket client;
	private UserModel user;
	private boolean abort;
	private boolean running;
	private BufferedReader br;
	private String accept;
	private PrintWriter pw;
	private String send = "123";
	private ConfigModel config;
	
	public Client(Socket client) {
		this.client = client;
		accept = "";
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
			running = false;
			return;
		}
		
		while(!abort && !client.isClosed() && accept != null) {
			try {
				accept = br.readLine();
				config = new ConfigModel(accept);
				send = Global.Server_CMD.deal(config);
				pw.println(send);
                pw.flush();
			}catch(Exception e) {
				;
			}
		}
		running = false;
		Global.Server_TCP.removeClient(this);
	}
	public void myAbort() {
		while(running) {
			abort = true;
		}
	}
	public boolean isRunning() {
		return running;
	}
}
