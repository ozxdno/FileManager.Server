package FileManager.Server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server_TCP {
	private static int port = 60000;
	private static Accept accept;
	private static Set<Client> clients = new HashSet<Client>();

	public static int getPort() {
		return port;
	}
	public static Set<Client> getClients(){
		return clients;
	}
	
	public static boolean setPort(int port) {
		if(port < 1000) {
			return false;
		}
		Server_TCP.port = port;
		return true;
	}
	
	public static void clear() {
		Server_TCP.port = 60000;
		accept = null;
		if(clients == null) {
			clients = new HashSet<Client>();
		}
	}
	public static boolean start(int port) {
		return restart(port);
	}
	public static boolean restart(int port) {
		stop();
		clear();
		setPort(port);
		try {
			accept = new Accept(new ServerSocket(Server_TCP.port));
			accept.start();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public static void stop() {
		if(accept != null) {
			accept.myAbort();
		}
	}
	public static void addClient(Client client) {
		clients.add(client);
	}
	public static void removeClient(Client client) {
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
				Server_TCP.addClient(client);
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
				pw.println(send);
                pw.flush();
			}catch(Exception e) {
				;
			}
		}
		running = false;
		Server_TCP.removeClient(this);
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
