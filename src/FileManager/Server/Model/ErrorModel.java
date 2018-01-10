package FileManager.Server.Model;

import FileManager.Server.Global.*;

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
	public boolean setMessage(String message) {
		if(message == null) {
			message = "";
		}
		this.message = message;
		return true;
	}
	
	public ErrorModel() {
		clear();
	}
	public ErrorModel(int code) {
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
	public void print() {
		System.out.println(toString());
	}
	public boolean show(int code) {
		this.code = code;
		this.time = Global.Time.getTicks();
		this.level = 0;
		String message = Global.ErrorType.getType(code);
		setMessage(message);
		print();
		return message != null;
	}
}
