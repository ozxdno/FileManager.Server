package FileManager.Server.Model;

import FileManager.Server.Global.*;

public class SupportModel {
	private String extension;
	private int type;
	private boolean registered;
	
	public String getExtension() {
		return extension;
	}
	public int getType() {
		return type;
	}
	
	public SupportModel(int type, String extension) {
		this.type = type;
		this.extension = Global.FixURL.fixExtension(extension);
	}
	
	public void register() {
		
	}
}