package FileManager.Server.Model;

public class InvitationModel {
	private UserModel user;
	private String code;
	
	public UserModel getUser() {
		return user;
	}
	public String getCode() {
		return code;
	}
	
	public boolean setUser(UserModel user) {
		if(user == null) {
			return false;
		}
		this.user = user;
		return true;
	}
	public boolean setCode(String code) {
		if(code == null || code.length() == 0) {
			return false;
		}
		this.code = code;
		return true;
	}
	
	public InvitationModel() {
		clear();
	}
	
	public void clear() {
		user = new UserModel("","");
		code = "";
	}
	public int register() {
		return 0;
	}
	
	public String toString() {
		return code + "|" + user.toString();
	}
}
