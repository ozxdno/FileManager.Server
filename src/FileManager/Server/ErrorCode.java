package FileManager.Server;

import java.util.*;

/**
 * 
 * @author ozxdno : 
 * 
 * 0x000000 - Normal; 
 * 0x00xxxx - Reserved;
 * 
 * 0x010101 - Server.Windows.Java.Command : Wrong Command; 
 *
 * 0x020101 - Server.Windows.C# ;
 *
 * 0x030101 - Server.Android ;
 * 
 * 0x100101 - Client.Windows.C# ;
 * 
 * 0x200101 - Client.Windows.Java ;
 * 
 * 0x300101 - Client.Broswer ;
 * 
 * 0x400101 - Client.Android ;
 *
 */
public final class ErrorCode {
	private static Map<Integer,String> message = new HashMap<Integer,String>();
	private static int code;
	
	public static int getError() {
		int code = ErrorCode.code;
		ErrorCode.code = 0;
		return code;
	}
	
	public static void setError(int code) {
		ErrorCode.code = code;
	}
	
	public static String getErrorMessage() {
		return message.get(code);
	}
	
	public static boolean setErrorMessage(int code, String message) {
		if(message == null || message.length() == 0) {
			return false;
		}
		if(ErrorCode.message.get(code) != null) {
			ErrorCode.message.replace(code, message);
			return true;
		}
		ErrorCode.message.put(code, message);
		return true;
	}
	
	public static String getString() {
		return String.format("%08x", code);
	}
}
