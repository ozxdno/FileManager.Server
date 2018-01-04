package FileManager.Server;

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
		this.extension = Tools.fixExtension(extension);
	}
	
	public void register() {
		
	}
}
