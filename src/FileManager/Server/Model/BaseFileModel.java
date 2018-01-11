package FileManager.Server.Model;

import java.io.*;

public class BaseFileModel {
	private long index;
	private String url;
	private int type;
	private int state;
	private long modify;
	private long length;
	private int score;
	private String tags;
	private boolean registered;
	
	public long getIndex() {
		return index;
	}
	public String getUrl() {
		return url;
	}
	public int getType() {
		return type;
	}
	public int getState() {
		return state;
	}
	public long getModify() {
		return modify;
	}
	public long getLength() {
		return length;
	}
	public int getScore() {
		return score;
	}
	public boolean isRegistered() {
		return registered;
	}
	public boolean setIndex(long index) {
		if(index < 0) {
			return false;
		}
		this.index = index;
		return true;
	}
	public boolean setUrl(String url) {
		if(url == null || url.length() == 0) {
			return false;
		}
		this.url = url;
		return true;
	}
	public boolean setType(int type) {
		if(type < 0) {
			return false;
		}
		this.type = type;
		return true;
	}
	public boolean setState(int state) {
		if(state < 0) {
			return false;
		}
		this.state = state;
		return true;
	}
	public boolean setModify(long modify) {
		if(modify < 0) {
			return false;
		}
		this.modify = modify;
		return true;
	}
	public boolean setLength(long length) {
		if(length < 0) {
			return false;
		}
		this.length = length;
		return true;
	}
	public boolean setTags(String tags) {
		if(tags == null || tags.length() == 0) {
			return false;
		}
		this.tags = tags;
		return true;
	}
	public boolean setRegistered(boolean registered) {
		this.registered = registered;
		return true;
	}
	
	
	public BaseFileModel() {
		clear();
	}
	public BaseFileModel(String url) {
		if(url == null || url.length() == 0) {
			clear();
			return;
		}
		init(new File(url));
	}
	public BaseFileModel(File file) {
		init(file);
	}
	
	public void clear() {
		index = -1;
		url = "";
		type = 0;
		state = 0;
		modify = -1;
		length = 0;
		score = 0;
		tags = "";
		registered = false;
	}
	public void copyValue(BaseFileModel model) {
		this.index = model.index;
		this.url = model.url;
		this.type = model.type;
		this.state = model.state;
		this.modify = model.modify;
		this.length = model.length;
		this.tags = model.tags;
		this.registered = model.registered;
	}
	
	protected void init(File file) {
		
	}
}

