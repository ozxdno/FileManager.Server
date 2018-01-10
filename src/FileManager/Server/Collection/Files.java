package FileManager.Server.Collection;

import java.util.HashMap;
import java.util.Map;
import FileManager.Server.Model.*;;

public class Files {
	private Map<Long, BaseFileModel> files = new HashMap<Long, BaseFileModel>();
	
	public Files() {
		clear();
	}
	
	public void clear() {
		if(files == null) {
			files = new HashMap<Long, BaseFileModel>();
		}
		files.clear();
	}
}