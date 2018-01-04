package FileManager.Server;

public class UserModel {
	/*
	 * unique
	 */
	private long index;
	/*
	 * unique, used to log in
	 */
	private String loginName;
	/*
	 * your preference name
	 */
	private String nickName;
	/*
	 * pw
	 */
	private String password;
	/*
	 * ip addresses
	 */
	private String ipv4;
	/*
	 * -1 - this user does not exist.
	 * 00 - cannot do anything refer to remote resource.
	 */
	private int priority;
	/*
	 * -1 - this user does not exist.
	 */
	private int level;
	/*
	 * exp
	 */
	private long experience;
	/*
	 * an url
	 */
	private String photoUrl;
	/*
	 * can use to by resource
	 */
	private long coins;
	/*
	 * the money you put in
	 */
	private double money;
	
	public long getIndex() {
		return index;
	}
	public String getLoginName() {
		return loginName;
	}
	public String nickName() {
		return nickName;
	}
	public String getPassword() {
		return password;
	}
	public int getPriority() {
		return priority;
	}
	public int getLevel() {
		return level;
	}
	public long getExperience() {
		return experience;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public long getCoins() {
		return coins;
	}
	public double getMoney() {
		return money;
	}
	
	public boolean setIndex(long index) {
		if(index < 0) {
			return false;
		}
		this.index = index;
		return true;
	}
	public boolean setLoginName(String loginName) {
		if(loginName == null || loginName.length() == 0) {
			return false;
		}
		this.loginName = loginName;
		return true;
	}
	public boolean setNickName(String nickName) {
		if(nickName == null || nickName.length() == 0) {
			return false;
		}
		this.nickName = nickName;
		return true;
	}
	public boolean setPassword(String password) {
		if(password == null || password.length() == 0) {
			return false;
		}
		this.password = password;
		return true;
	}
	
	public UserModel(String loginName, String password) {
		init(loginName,password);
	}
	
	public void clear() {
		index = -1;
		loginName = "";
		nickName = "";
		password = "";
		priority = 0;
		level = 0;
		experience = 0;
		photoUrl = "";
		coins = 0;
		money = 0.0;
	}
	public int register() {
		return -1;
	}
	
	protected void init(String loginName, String password) {
		clear();
		setLoginName(loginName);
		setPassword(password);
	}
}
