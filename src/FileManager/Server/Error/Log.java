package FileManager.Server.Error;

import FileManager.Server.Collection.*;
import FileManager.Server.Global.*;

public class Log  {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Errors content;
	private String url;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Errors getContent() {
		return content;
	}
	public String getUrl() {
		return url;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setContent(Errors content) {
		if(content == null) {
			return false;
		}
		this.content = content;
		return true;
	}
	public boolean setUrl(String url) {
		if(url == null || url.length() == 0) {
			return false;
		}
		this.url = url;
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Log() {
		clear();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		if(content == null) {
			content = new Errors();
		}
		content.clear();
		url = Global.Path.getProjectPath()  + "\\src\\FileManager\\Server\\Error\\Log";
	}
}
