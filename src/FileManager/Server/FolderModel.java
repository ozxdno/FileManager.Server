package FileManager.Server;

import java.io.*;

public class FolderModel extends BaseFileModel {
	private boolean isRoot;
	private Folders folders = new Folders();
	private Files files = new Files();
	
	public boolean getIsRoot() {
		return isRoot;
	}
	public Folders getFolders() {
		return folders;
	}
	public Files getFiles() {
		return files;
	}
	
	public FolderModel() {
		clear();
	}
	public FolderModel(String url) {
		if(url == null || url.length() == 0) {
			clear();
		}
		init(new File(url));
	}
	
	public void clear() {
		super.clear();
		folders.clear();
		files.clear();
	}
	public void changeRootPath() {
		
	}
	
	protected void init(File folder) {
		
	}
}
