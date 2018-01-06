package FileManager.Server;

import java.net.*;
import java.io.*;

public class Client_TCP {
	private static String IpAddress;
    private static InetAddress ip;
    private static int port;
    private static Socket client;
    private static DataInputStream recestream;
    private static String recestr;
    private static byte[] recebyte;
    private static DataOutputStream sendstream;
    private static String sendstr;
    private static byte[] sendbyte;

    public static String getRecestr() {
        return recestr;
    }

    public static boolean start() {
        clear();
        if(client == null){
            return false;
        }

        try {
            sendstream = new DataOutputStream(client.getOutputStream());
            sendstr = "test";
            sendbyte = sendstr.getBytes();
            sendstream.write(sendbyte);

            recestream =  new DataInputStream(client.getInputStream());
            recestream.read(recebyte);
            recestr = new String(recebyte);
        } catch (Exception e) {
            ;
        }

        return true;
    }

    public static void clear() {
        IpAddress = "172.24.136.41";
        try {
            ip = InetAddress.getByName(IpAddress);
        } catch (Exception e) {
            ;
        }
        port = 60000;
        try {
            client = new Socket(ip, port);
        } catch (Exception e) {
            client = null;
        }
        
        recestream = null;
        recestr = "";
        recebyte = new byte[1024];
        sendstream = null;
        sendstr = "";
        sendbyte = null;
    }
}
