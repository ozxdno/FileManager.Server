package FileManager.Server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server_UDP_SingleThread {
	private static int port = 65535;
	private static Driver driver;
	private static Users users;
	
	public static int getPort() {
		return port;
	}
	
	public static boolean setPort(int port) {
		if(port < 1000) {
			return false;
		}
		Server_UDP_SingleThread.port = port;
		return true;
	}
	
	public static void clear() {
		port = 60000;
	}
	
	public static boolean restart(int port) {
		stop();
		setPort(port);
		try {
			driver = new Driver(new DatagramSocket(Server_UDP_SingleThread.port));
			driver.start();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public static void stop() {
		if(driver != null) {
			driver.myAbort();
		}
	}
	public static boolean send(DatagramPacket data) {
		return driver.send(data);
	}
}

class Driver extends Thread {
	private DatagramSocket server;
	private DatagramPacket data;
	private int edition;
	private Processor client;
	private Processor2 client2;
	private boolean abort;
	private boolean running;
	private byte[] recebyte;
	
	public Driver(DatagramSocket server) {
		this.server = server;
		edition = 1;
		abort = false;
		running = false;
		recebyte = new byte[1024];
		data = new DatagramPacket(recebyte, recebyte.length);
		super.setName("Server UDP Driver");
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
				server.receive(data);
				if(edition == 1) {
					client = new Processor(data);
					//client.start();
					client.run();
				} else if(edition == 2) {
					client2 = new Processor2(recebyte.clone());
					//client2.start();
					client2.run();
				}
			}catch(Exception e) {
				running = true;
			}
			break;
		}
		running = false;
	}
	public boolean send(DatagramPacket data) {
		try {
			server.send(data);
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
}

class Processor extends Thread {
	private DatagramPacket data;
	private DatagramPacket echo;
	private boolean abort;
	private boolean running;
	private String accept;
	private String send = "221133";
	private byte[] sendbyte;
	
	public Processor(DatagramPacket data) {
		this.data = data;
		abort = false;
		running = false;
		super.setName("Server UDP Processor");
	}
	
	public void run() {
		abort = false;
		running = true;
		if(data == null) {
			running = false;
			return;
		}
		while(!abort) {
			try {
				accept = "";
				accept = accept + data.getAddress().toString() + "|";
				accept = accept + String.valueOf(data.getPort()) + "|";
				accept = accept + new String(data.getData(), 0, data.getLength());
				
				sendbyte = send.getBytes();
				//echo = new DatagramPacket(sendbyte, sendbyte.length, data.getAddress(), data.getPort());
				//Server_UDP_SingleThread.send(echo);
			}catch(Exception e) {
				running = true;
			}
			
			break;
		}
		running = false;
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

class Processor2 extends Thread {
	private boolean abort;
	private boolean running;
	private String receive;
	private String send = "221133";
	private byte[] recebyte;
	private byte[] sendbyte;
	
	public Processor2(byte[] receive) {
		this.recebyte = receive;
		abort = false;
		running = false;
		super.setName("Server UDP Processor2");
	}
	
	public void run() {
		abort = false;
		running = true;
		if(recebyte == null) {
			running = false;
			return;
		}
		while(!abort) {
			try {
				Byte2String();
				
				sendbyte = send.getBytes();
				//echo = new DatagramPacket(sendbyte, sendbyte.length, data.getAddress(), data.getPort());
				//Server_UDP_SingleThread.send(echo);
			}catch(Exception e) {
				running = true;
			}
			
			break;
		}
		running = false;
	}
	public void myAbort() {
		while(running) {
			abort = true;
		}
	}
	public boolean isRunning() {
		return running;
	}
	
	private void Byte2String() {
		int b = 0;
		for(int i=0; i<recebyte.length; i++) {
			b = recebyte[i];
			if(b == 0 || b == 10 || b == 13) {
				recebyte[i] = 0;
				break;
			}
		}
		receive = new String(recebyte);
	}
}
