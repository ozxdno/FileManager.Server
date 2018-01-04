package FileManager.Server;

public class Server {
	private static int errorCode;
	
	public static int getErrorCode() {
		int tempErrorCode = errorCode;
		errorCode = 0;
		return tempErrorCode;
	}
	
	public static boolean setErrorCode(int code) {
		if(code < 0) {
			return false;
		}
		errorCode = code;
		return true;
	}
}
