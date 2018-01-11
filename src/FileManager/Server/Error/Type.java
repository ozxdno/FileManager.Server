package FileManager.Server.Error;

import java.util.*;
import FileManager.Server.Model.*;
import FileManager.Server.Global.*;

public class Type {
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Map<Integer,String> types;
	private String url;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Map<Integer,String> getTypes() {
		return types;
	}
	public String getType(int code) {
		return types.get(code);
	}
	public String getUrl() {
		return url;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setUrl(String url) {
		if(url == null || url.length() == 0) {
			return false;
		}
		this.url = url;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Type() {
		clear();
	}
	public Type(String url) {
		clear();
		setUrl(url);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		if(types == null) {
			types = new HashMap<Integer,String>();
		}
		types.clear();
		url = Global.Path.getProjectPath() + "\\src\\FileManager\\Server\\Error\\Type";
	}
	public boolean exists(int code) {
		return getType(code) != null;
	}
	public boolean load() {
		FileManager.Server.Load.Text type = new FileManager.Server.Load.Text(url);
		if(!type.exists()) {
			return false;
		}
		type.load();
		while(!type.isEmpty()) {
			ConfigModel c = type.getNext();
			try {
				types.put(Integer.parseInt(c.getField()), c.getValue());
			} catch(Exception e) {
				Global.CurrentError.show(25);
			}
		}
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
}
