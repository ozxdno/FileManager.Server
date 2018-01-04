package FileManager.Server;

public final class ErrorCode {
	/*
	 * 00 - no error
	 */
	private static int code;
	
	public static int getError() {
		int code = ErrorCode.code;
		ErrorCode.code = 0;
		return code;
	}
	
	public static void setError(int code) {
		ErrorCode.code = code;
	}
}
