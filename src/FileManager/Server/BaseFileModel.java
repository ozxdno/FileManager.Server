package FileManager.Server;

import java.io.*;

public class BaseFileModel {
	/*
	 * unique index
	 */
	private long index;
	/*
	 * the resource location
	 */
	private String url;
	/*
	 * 000 - unsupport
	 * 001 - picture
	 * 002 - gif
	 * 003 - music
	 * 004 - frame
	 * 005 - video
	 * 006 - gal
	 * 007 - rpg
	 * 008 - 3d game
	 * 009 - process
	 * 010 - text
	 */
	private int type;
	/*
	 * 0 - not exist in remote
	 * 1 - not exist in local
	 * 2 - downloading
	 * 3 - downloaded
	 * 4 - prepared
	 * 5 - done
	 */
	private int state;
	/*
	 * file last modify time
	 */
	private long modify;
	/*
	 * file size, unit : B(Byte);
	 */
	private long length;
	/*
	 * your preference
	 */
	private int score;
	/*
	 * means add this to files and save to database
	 */
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
		registered = false;
	}
	public int moveTo(String destUrl, boolean cover) {
		return -1;
	}
	
	protected void init(File file) {
		
	}
}
