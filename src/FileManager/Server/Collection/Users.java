package FileManager.Server.Collection;

import java.sql.ResultSet;
import java.util.*;

import FileManager.Server.Global.Global;
import FileManager.Server.Model.*;
import FileManager.Server.OperateDB.SQL;;

public class Users {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Map<String, UserModel> users = new HashMap<String, UserModel>();
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<UserModel> getUsers(){
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		for(UserModel u : users) {
			users.add(u);
		}
		return users;
	}
	public UserModel getUser(String loginName) {
		return users.get(loginName);
	}
	public UserModel getUser(long index) {
		for(UserModel u : users.values()) {
			if(u.getIndex() == index) {
				return u;
			}
		}
		return null;
	}
	public UserModel getUser(ResultSet set) {
		try {
			UserModel u = new UserModel();
			u.setIndex(set.getLong("Index"));
			u.setLoginName(set.getString("LoginName"));
			u.setNickName(set.getString("NickName"));
			u.setPassword(set.getString("Password"));
			u.setEmail(set.getString("Email"));
			u.setPhone(set.getString("Phone"));
			u.setState(set.getInt("State"));
			u.setPriority(set.getInt("Priority"));
			u.setLevel(set.getInt("Level"));
			u.setExperience(set.getLong("Experience"));
			u.setPhotoUrl(set.getString("PhotoUrl"));
			u.setCoins(set.getLong("Coins"));
			u.setMoney(set.getDouble("Money"));
			return u;
		} catch(Exception e) {
			Global.CurrentError.show(31);
			return null;
		}
	}
	public String getSqlTableCols() {
		String cols = 
				"`Index` BIGINT NOT NULL,"
				+ "`LoginName` VARCHAR(100) NOT NULL,"
				+ "`NickName` VARCHAR(100) NOT NULL, "
				+ "`Password` VARCHAR(100) NOT NULL, "
				+ "`Email` VARCHAR(100) NOT NULL, "
				+ "`Phone` VARCHAR(100) NOT NULL, "
				+ "`State` INT NOT NULL, "
				+ "`Priority` INT NOT NULL, "
				+ "`Level` INT NOT NULL, "
				+ "`Experience`BIGINT NOT NULL, "
				+ "`PhotoUrl` VARCHAR(1024) NOT NULL,"
				+ "`Coins` BIGINT NOT NULL, "
				+ "`Money` DOUBLE NOT NULL ";
		return cols;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Users() {
		clear();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		if(users == null) {
			users = new HashMap<String, UserModel>();
		}
		users.clear();
	}
	public boolean exists(String loginName) {
		return getUser(loginName) != null;
	}
	public boolean load() {
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			return false;
		}
		ResultSet set = sql.query("SELECT * FROM Users;");
		if(set == null) {
			String s = "CREATE TABLE Users(" + getSqlTableCols() + ");";
			return sql.updata(s);
		}
		try {
			while(set.next()) {
				UserModel u = getUser(set);
				if(u != null) {
					users.put(u.getLoginName(), u);
				}
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(29);
			return false;
		}
	}
	public boolean addUser(UserModel user) {
		users.put(user.getLoginName(), user);
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
