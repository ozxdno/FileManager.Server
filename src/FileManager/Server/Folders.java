package FileManager.Server;

import java.util.*;

public class Folders {
	private Map<Long, FolderModel> folders = new HashMap<Long, FolderModel>();
	
	public Folders() {
		clear();
	}
	
	public void clear() {
		if(folders == null) {
			folders = new HashMap<Long, FolderModel>();
		}
		folders.clear();
	}
}
