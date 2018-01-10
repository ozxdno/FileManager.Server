package FileManager.Server.Collection;

import java.util.HashMap;
import java.util.Map;
import FileManager.Server.Model.*;

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
