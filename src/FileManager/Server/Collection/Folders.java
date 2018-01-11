package FileManager.Server.Collection;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import FileManager.Server.Global.Global;
import FileManager.Server.Model.*;
import FileManager.Server.OperateDB.SQL;

public class Folders extends BaseFileModel {
	private Map<Long, FolderModel> folders = new HashMap<Long, FolderModel>();
	
	public Map<Long, FolderModel> getFolders() {
		return folders;
	}
	public FolderModel getFolder(long index) {
		return folders.get(index);
	}
	public FolderModel getFolder(ResultSet set) {
		FolderModel f = new FolderModel();
		f.copyValue(Global.Files.getFile(set));
		return f;
	}
	public String getSqlTableCols() {
		String cols = Global.Files.getSqlTableCols() + 
				", `Folders` VARCHAR(1024) NOT NULL, "
				+ "`Files` VARCHAR(1024) NOT NULL, "
				+ "`IsRoot` BOOL NOT NULL";
		return cols;
	}
	public boolean setFolders(Map<Long, FolderModel> folders) {
		if(folders == null) {
			return false;
		}
		this.folders = folders;
		return true;
	}
	
	public Folders() {
		clear();
	}
	
	public void clear() {
		super.clear();
		if(folders == null) {
			folders = new HashMap<Long, FolderModel>();
		}
	}
	public int size() {
		return folders.size();
	}
	public boolean addFolder(FolderModel folder) {
		if(folder == null) {
			return false;
		}
		folders.put(folder.getIndex(), folder);
		return true;
	}
	public boolean load() {
		if(Global.Files.size() == 0) {
			Global.CurrentError.show(30);
		}
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			return false;
		}
		ResultSet set = sql.query("SELECT * FROM Folders;");
		if(set == null) {
			String s = "CREATE TABLE Folders(" + getSqlTableCols() + ");";
			return sql.updata(s);
		}
		try {
			while(set.next()) {
				FolderModel f = getFolder(set);
				folders.put(f.getIndex(), f);
			}
		} catch(Exception e) {
			Global.CurrentError.show(29);
			return false;
		}
		
		set = sql.query("SELECT * FROM Folders;");
		try {
			while(set.next()) {
				FolderModel f = getFolder(set.getLong("Index"));
				
				String subfolders = set.getString("Folders");
				ConfigModel c = new ConfigModel();
				c.setValue(subfolders);
				for(int i=0; i<c.getItemsSize(); i++) {
					FolderModel subfolder = getFolder(c.getLong(i));
					if(subfolder != null) {
						f.addFolder(subfolder);
					}
					
				}
				String subfiles = set.getString("Files");
				c.setValue(subfiles);
				for(int i=0; i<c.getItemsSize(); i++) {
					BaseFileModel subfile = Global.Files.getFile(c.getLong(i));
					if(subfile != null) {
						f.addFile(subfile);
					}
				}
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(29);
			return false;
		}
	}
}
