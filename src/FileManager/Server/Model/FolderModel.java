package FileManager.Server.Model;

import java.io.File;
import FileManager.Server.Collection.*;

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
	public FolderModel getFolder(long index) {
		return folders.getFolder(index);
	}
	public Files getFiles() {
		return files;
	}
	public BaseFileModel getFile(int index) {
		return files.getFile(index);
	}
	
	public boolean setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
		return true;
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
	public int sizeOfFolders() {
		return folders.size();
	}
	public int sizeOfFiles() {
		return files.size();
	}
	public int size() {
		return sizeOfFolders() + sizeOfFiles();
	}
	public boolean addFolder(FolderModel folder) {
		return folders.addFolder(folder);
	}
	public boolean addFile(BaseFileModel file) {
		return files.addFile(file);
	}
	protected void init(File folder) {
		
	}
}