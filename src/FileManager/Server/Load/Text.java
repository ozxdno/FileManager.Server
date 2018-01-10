package FileManager.Server.Load;

import java.io.*;
import FileManager.Server.Collection.*;
import FileManager.Server.Global.*;
import FileManager.Server.Model.*;

public class Text {
	private Configs content;
	private String url;
	private File file;
	
	public String getUrl() {
		return url;
	}
	public ConfigModel getConfig(String field) {
		return content.getConfig(field);
	}
	public ConfigModel getNext() {
		return content.getNext();
	}
	public boolean setUrl(String url) {
		String prevUrl = this.url;
		File prevFile = this.file;
		this.url = url;
		file = new File(url);
		if(file.exists() && file.isFile()) {
			return true;
		} else {
			if(!file.exists()) {
				Global.CurrentError.show(20);
			} else {
				Global.CurrentError.show(21);
			}
			this.url = prevUrl;
			this.file = prevFile;
			return false;
		}
	}
	
	public Text() {
		clear();
	}
	public Text(String url) {
		clear();
		setUrl(url);
	}
	
	public void clear() {
		if(content == null) {
			content = new Configs();
		}
		content.clear();
		url = null;
		file = null;
	}
	public boolean exists() {
		return file != null && file.exists() && file.isFile();
	}
	public boolean load() {
		if(!exists()) {
			return false;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while(line != null && line.length() != 0) {
				ConfigModel config = new ConfigModel(line);
				content.addConfig(config);
			}
			try {
				br.close();
			} catch(Exception e) {
				Global.CurrentError.show(24);
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(23);
			return false;
		}
	}
	public boolean isEmpty() {
		return content.isEmpty();
	}
	public int size() {
		return content.size();
	}
}
