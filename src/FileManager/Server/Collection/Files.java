package FileManager.Server.Collection;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import FileManager.Server.Global.Global;
import FileManager.Server.Model.*;
import FileManager.Server.OperateDB.SQL;;

public class Files {
	private Map<Long, BaseFileModel> files = new HashMap<Long, BaseFileModel>();
	
	public Map<Long, BaseFileModel> getFiles() {
		return files;
	}
	public BaseFileModel getFile(long index) {
		return files.get(index);
	}
	public BaseFileModel getFile(ResultSet set) {
		try {
			BaseFileModel f = new BaseFileModel();
			f.setIndex(set.getLong("Index"));
			f.setUrl(set.getString("Url"));
			f.setType(set.getInt("Type"));
			f.setState(set.getInt("State"));
			f.setModify(set.getInt("Modify"));
			f.setLength(set.getInt("Length"));
			f.setTags(set.getString("Tags"));
			f.setRegistered(true);
			return f;
		} catch(Exception e) {
			Global.CurrentError.show(29);
			return null;
		}
	}
	public String getSqlTableCols() {
		String cols = 
				"`Index` BIGINT NOT NULL, "
				+ "`Url` VARCHAR(1024) NOT NULL, "
				+ "`Type` INT NOT NULL, "
				+ "`State` INT NOT NULL, "
				+ "`Modify` BIGINT NOT NULL, "
				+ "`Length` BIGINT NOT NULL, "
				+ "`Score` INT NOT NULL, "
				+ "`Tags` VARCHAR(1024) NOT NULL";
		return cols;
	}
	
	public Files() {
		clear();
	}
	
	public void clear() {
		if(files == null) {
			files = new HashMap<Long, BaseFileModel>();
		}
		files.clear();
	}
	public int size() {
		return files.size();
	}
	public boolean addFile(BaseFileModel file) {
		if(file == null) {
			return false;
		}
		files.put(file.getIndex(), file);
		return true;
	}
	public boolean load() {
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			return false;
		}
		ResultSet set = sql.query("SELECT * FROM Files;");
		if(set == null) {
			String s = "CREATE TABLE Files(" + getSqlTableCols() + ");";
			return sql.updata(s);
		}
		try {
			while(set.next()) {
				BaseFileModel f = getFile(set);
				files.put(f.getIndex(), f);
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(29);
			return false;
		}
	}
}