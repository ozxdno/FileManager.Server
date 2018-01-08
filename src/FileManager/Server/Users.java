package FileManager.Server;

import java.util.*;

public class Users {
	private Map<String, UserModel> users = new HashMap<String, UserModel>();
	
	public Users() {
		clear();
	}
	
	public void clear() {
		if(users == null) {
			users = new HashMap<String, UserModel>();
		}
		users.clear();
	}
	public UserModel getUser(String loginName, String password) {
		if(loginName == null || loginName.length() == 0) {
			ErrorCode.setError(0);
			return null;
		}
		if(password == null || password.length() == 0) {
			return null;
		}
		
		UserModel user = users.get(loginName);
		if(user == null) {
			return null;
		}
		if(!user.getLoginName().equals(loginName)) {
			return null;
		}
		if(!user.getPassword().equals(password)) {
			return null;
		}
		
		return user;
	}
	public ArrayList<UserModel> getUsers(){
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		for(UserModel u : users) {
			users.add(u);
		}
		return users;
	}
	
	public boolean exists(String loginName) {
		if(loginName == null || loginName.length() == 0) {
			return false;
		}
		
		return users.get(loginName) != null;
	}
}
