package FileManager.Server.Model;

public class ErrorModel {
	private int code;
	private long time;
	private int level;
	private String message;
	
	public int getCode() {
		return code;
	}
	
	public boolean setCode(int code) {
		this.code = code;
		return true;
	}
	
	public ErrorModel() {
		clear();
	}
	
	public void clear() {
		code = 0;
		time = 0;
		level = 0;
		message = "";
	}
	public String toString() {
		return String.valueOf(code) + " " + String.valueOf(time) + " " + String.valueOf(level) + " " + message;
	}
	public boolean toThis(String str) {
		if(str == null || str.length() == 0) {
			return false;
		}
		
	    return true;
	}
}
