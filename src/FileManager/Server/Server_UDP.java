package FileManager.Server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server_UDP {
	private static int port = 65535;
	private static UDP_Server server;
	private static Set<UDP_Client> clients;
	
	public static int getPort() {
		return port;
	}
	public static Set<UDP_Client> getClients(){
		return clients;
	}
	
	public static boolean setPort(int port) {
		if(port < 1000) {
			return false;
		}
		Server_UDP.port = port;
		return true;
	}
	
	public static void clear() {
		port = 65535;
		server = null;
		if(clients == null) {
			clients = new HashSet<UDP_Client>();
		}
		clients.clear();
	}
	
	public static boolean restart(int port) {
		stop();
		clear();
		setPort(port);
		try {
			server = new UDP_Server(new DatagramSocket(Server_UDP.port));
			server.start();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public static void stop() {
		if(server != null) {
			server.myAbort();
		}
	}
	public static boolean addClient(UDP_Client client) {
		clients.add(client);
		return true;
	}
	public static void removeClient(UDP_Client client) {
		clients.remove(client);
	}
	public static UserModel getUser(String loginName) {
		for(UDP_Client c : clients) {
			if(c != null && c.getUser() != null && c.getUser().getLoginName().equals(loginName)) {
				return c.getUser();
			}
		}
		
		return null;
	}
}

class UDP_Server extends Thread {
	private DatagramSocket server;
	private DatagramPacket rece;
	private DatagramPacket send;
	private UDP_Client client;
	private boolean abort;
	private boolean running;
	private byte[] recebyte;
	private byte[] sendbyte;
	private String recestr;
	private String sendstr;
	
	public UDP_Server(DatagramSocket server) {
		this.server = server;
		recebyte = new byte[1024];
		recestr = "";
		rece = new DatagramPacket(recebyte, recebyte.length);
		sendbyte = null;
		sendstr = null;
		send = null;
		abort = false;
		running = false;
		super.setName("UDP_Server");
	}
	
	public void run() {
		abort = false;
		running = true;
		if(server == null) {
			running = false;
			return;
		}
		while(!abort) {
			try {
				server.receive(rece);
				InetAddress ipv4 = rece.getAddress();
				int port = rece.getPort();
				receive();
				
				if(recestr.equals("apply for new port")) {
					DatagramSocket newServer = seekNewServer();
					if(newServer == null) {
						send("too many users, please try again later!", ipv4, port);
					} else {
						client = new UDP_Client(newServer);
						Server_UDP.addClient(client);
						client.start();
						send("new port = " + String.valueOf(client.getPort()), ipv4, port);
					}
				} else {
					send("wrong command!", ipv4, port);
				}
				
			}catch(Exception e) {
				;
			}
		}
		running = false;
	}
	public boolean send(String str, InetAddress ipv4, int port) {
		if(server == null) {
			return false;
		}
		sendstr = str;
		sendbyte = sendstr.getBytes();
		send = new DatagramPacket(sendbyte, sendbyte.length, ipv4, port);
		try {
			server.send(send);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public void myAbort() {
		try {
			if(server != null) {
				server.close();
			}
		}catch(Exception e) {
			;
		}
		
		while(running) {
			abort = true;
		}
		
		try {
			if(server != null) {
				server.close();
			}
		}catch(Exception e) {
			;
		}
	}
	public boolean isRunning() {
		return running;
	}
	
	private DatagramSocket seekNewServer() {
		DatagramSocket server;
		for(int port = Server_UDP.getPort()-1; port > 1000; port--) {
			try {
				server = new DatagramSocket(port);
				return server;
			}catch(Exception e) {
				;
			}
		}
		return null;
	}
	private void receive() {
		recestr = new String(rece.getData(), 0, rece.getLength());
		if(recestr == null || recestr.length() == 0) {
			return;
		}
		int idx1 = recestr.indexOf('\n');
		int idx2 = recestr.indexOf('\r');
		if(idx1 == -1 && idx2 == -1) {
			return;
		}
		if(idx1 != -1 && idx2 != -1) {
			if(idx1 < idx2) {
				idx2 = -1;
			} else {
				idx1 = -1;
			}
		}
		if(idx1 != -1 && idx2 == -1) {
			recestr = recestr.substring(0, idx1-1);
			return;
		}
		if(idx1 == -1 && idx2 != -1) {
			recestr = recestr.substring(0, idx2-1);
			return;
		}
	}
}

class UDP_Client {
	private int port;
	private DatagramSocket server;
	private DatagramPacket rece;
	private DatagramPacket send;
	private boolean abort;
	private boolean running;
	private byte[] recebyte;
	private byte[] sendbyte;
	private String recestr;
	private String sendstr;
	private UserModel user;
	private processor pro;
	
	public int getPort() {
		try {
			port = server.getPort();
		} catch(Exception e) {
			;
		}
		
		return port;
	}
	public UserModel getUser() {
		return user;
	}
	
	public boolean setPort(int port) {
		if(port < 1000) {
			return false;
		}
		this.port = port;
		return true;
	}
	public boolean setServer(DatagramSocket server) {
		this.server = server;
		return true;
	}
	public UDP_Client(DatagramSocket server) {
		stop();
		clear();
		setServer(server);
		getPort();
	}
	
	public void clear() {
		port = 0;
		server = null;
		rece = null;
		send = null;
		abort = false;
		running = false;
		recebyte = null;
		sendbyte = null;
		recestr = null;
		sendstr = null;
		user = null;
	}
	public boolean start() {
		stop();
		pro = new processor(this);
		pro.start();
		return true;
	}
	public void stop() {
		if(pro != null) {
			pro.myAbort();
		}
	}
	public boolean send(String str) {
		return true;
	}
	
	private class processor extends Thread {
		private UDP_Client parent;
		
		public processor(UDP_Client parent) {
			this.parent = parent;
			super.setName("UDP_Client");
		}
		
		public void run() {
			abort = false;
			running = true;
			if(server == null) {
				running = false;
				return;
			}
			while(!abort) {
				try {
					server.receive(rece);
					InetAddress ipv4 = rece.getAddress();
					int port = rece.getPort();
					receive();
					
				}catch(Exception e) {
					;
				}
			}
			running = false;
			Server_UDP.removeClient(parent);
		}
		public void myAbort() {
			while(running) {
				abort = true;
			}
		}
		public boolean isRunning() {
			return running;
		}
		
		private void receive() {
			recestr = new String(rece.getData(), 0, rece.getLength());
			if(recestr == null || recestr.length() == 0) {
				return;
			}
			int idx1 = recestr.indexOf('\n');
			int idx2 = recestr.indexOf('\r');
			if(idx1 == -1 && idx2 == -1) {
				return;
			}
			if(idx1 != -1 && idx2 != -1) {
				if(idx1 < idx2) {
					idx2 = -1;
				} else {
					idx1 = -1;
				}
			}
			if(idx1 != -1 && idx2 == -1) {
				recestr = recestr.substring(0, idx1-1);
				return;
			}
			if(idx1 == -1 && idx2 != -1) {
				recestr = recestr.substring(0, idx2-1);
				return;
			}
		}
	}
}
