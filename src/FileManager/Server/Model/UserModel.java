package FileManager.Server.Model;

import FileManager.Server.Global.*;
import FileManager.Server.OperateDB.*;

public class UserModel {

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private long index;
	private String loginName;
	private String nickName;
	private String password;
	private String email;
	private String phone;
	private int state;
	private int priority;
	private int level;
	private long experience;
	private String photoUrl;
	private long coins;
	private double money;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	public int getState() {
		return state;
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setIndex(long index) {
		if(index < 0) {
			return false;
		}
		this.index = index;
		return true;
	}
	public boolean setNextIndex(SQL sql) {
		ConfigModel c = Global.Configs.getConfig_NotRemove("NextIndex_User");
		long nextIndex = c.getLong();
		String s = "SET SQL_SAFE_UPDATES = 0;";
		if(!sql.updata(s)) {
			return false;
		}
		s = "update Configs set `Value` = '" + String.valueOf(nextIndex+1) + "' where `Field` = 'NextIndex_User';";
		if(!sql.updata(s)) {
			return false;
		}
		s = "SET SQL_SAFE_UPDATES = 1;";
		if(!sql.updata(s)) {
			return false;
		}
		
		c.setValue(String.valueOf(nextIndex+1));
		this.index = nextIndex;
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
	public boolean setEmail(String email) {
		if(email == null || email.length() == 0) {
			return false;
		}
		this.email = email;
		return true;
	}
	public boolean setPhone(String phone) {
		if(phone == null || phone.length() == 0) {
			return false;
		}
		this.phone = phone;
		return true;
	}
	public boolean setState(int state) {
		if(state < 0) {
			return false;
		}
		this.state = state;
		return true;
	}
	public boolean setPriority(int priority) {
		if(priority < 0) {
			return false;
		}
		this.priority = priority;
		return true;
	}
	public boolean setLevel(int level) {
		if(level < 0) {
			return false;
		}
		this.level = level;
		return true;
	}
	public boolean setExperience(long experience) {
		if(experience < 0) {
			return false;
		}
		this.experience = experience;
		return true;
	}
	public boolean setPhotoUrl(String photoUrl) {
		if(photoUrl == null || photoUrl.length() == 0) {
			return false;
		}
		this.photoUrl = photoUrl;
		return true;
	}
	public boolean setCoins(long coins) {
		if(coins < 0) {
			coins = 0;
		}
		this.coins = coins;
		return true;
	}
	public boolean setMoney(double money) {
		this.money = money;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public UserModel() {
		clear();
	}
	public UserModel(String loginName, String password) {
		clear();
		setLoginName(loginName);
		setPassword(password);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		index = 0;
		loginName = "";
		nickName = "";
		password = "";
		state = Global.Enum.UserState_Offline;
		priority = Global.Enum.UserPriority_Guest;
		level = Global.Enum.UserLevel_0;
		experience = 0;
		photoUrl = "";
		coins = 0;
		money = 0.0;
	}
	public boolean register() {
		if(Global.Users.exists(loginName)) {
			Global.CurrentError.show(40);
			return false;
		}
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			Global.CurrentError.show(40);
			return false;
		}
		if(!setNextIndex(sql)) {
			Global.CurrentError.show(40);
			return false;
		}
		String s = "INSERT INTO Users VALUES(" +
				String.valueOf(index) + "," +
				"'" + loginName + "'," +
				"'" + nickName + "'," +
				"'" + password + "'," +
				"'" + email + "'," +
				"'" + phone + "'," +
				String.valueOf(state) + "," +
				String.valueOf(priority) + "," +
				String.valueOf(level) + "," +
				String.valueOf(experience) + "," +
				"'" + photoUrl + "'," +
				String.valueOf(coins) + "," +
				String.valueOf(money) + ");";
		if(!sql.updata(s)) {
			Global.CurrentError.show(40);
			return false;
		}
		Global.Users.addUser(this);
		return true;
	}
	public int delete() {
		return -1;
	}
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	public String toString() {
		String str = "";
		str += String.valueOf(index) + "|";
		str += loginName + "|";
		str += nickName + "|";
		str += password + "|";
		str += email + "|";
		str += phone + "|";
		str += String.valueOf(state) + "|";
		str += String.valueOf(priority) + "|";
		str += String.valueOf(level) + "|";
		str += String.valueOf(experience) + "|";
		str += photoUrl + "|";
		str += String.valueOf(coins) + "|";
		str += String.valueOf(money);
		
		return str;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
