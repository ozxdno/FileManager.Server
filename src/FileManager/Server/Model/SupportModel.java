package FileManager.Server.Model;

import FileManager.Server.Global.*;

public class SupportModel {
	private String extension;
	private int type;
	private String show;
	private String hide;
	
	public String getExtension() {
		return extension;
	}
	public int getType() {
		return type;
	}
	public String getShowExtension() {
		return show;
	}
	public String getHideExtension() {
		return hide;
	}
	public boolean setExtension(String extension) {
		if(extension == null) {
			return false;
		}
		this.extension = Global.FixURL.fixExtension(extension);
		return true;
	}
	public boolean setType(int type) {
		if(type < 0) {
			return false;
		}
		this.type = type;
		return true;
	}
	public boolean setShowExtension(String show) {
		if(show == null) {
			return false;
		}
		this.show = Global.FixURL.fixExtension(show);
		return true;
	}
	public boolean setHideExtension(String hide) {
		if(hide == null) {
			return false;
		}
		this.hide = Global.FixURL.fixExtension(hide);
		return true;
	}
	
	public SupportModel() {
		clear();
	}
	public SupportModel(int type, String extension) {
		clear();
		setType(type);
		setExtension(extension);
	}
	
	public void clear() {
		extension = "";
		type = Global.Enum.File_Unsupport;
		show = "";
		hide = "";
	}
}