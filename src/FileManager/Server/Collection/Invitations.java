package FileManager.Server.Collection;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import FileManager.Server.Global.*;
import FileManager.Server.Model.*;
import FileManager.Server.OperateDB.SQL;

public class Invitations {
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Map<String,InvitationModel> invitations = new HashMap<String,InvitationModel>();
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Map<String,InvitationModel> getInvitations() {
		return invitations;
	}
	public InvitationModel getInvitation(String code) {
		return invitations.get(code);
	}
	public InvitationModel getInvitation(ResultSet set) {
		try {
			InvitationModel i = new InvitationModel();
			i.setCode(set.getString("Code"));
			UserModel u = Global.Users.getUser(set);
			i.setUser(u);
			return i;
		} catch (Exception e) {
			Global.CurrentError.show(32);
			return null;
		}
	}
	public UserModel getUserModel(String code) {
		InvitationModel i = getInvitation(code);
		if(i == null) {
			return null;
		}
		return i.getUser();
	}
	public String getSqlTableCols() {
		String cols = "`Code` VARCHAR(100) NOT NULL ," +
			Global.Users.getSqlTableCols();
		return cols;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setInvitation(InvitationModel invitation) {
		return invitation.register() == 0;
	}
	public boolean setSqlDefaultRows(SQL sql) {
		boolean ok = sql.updata("INSERT INTO Invitations VALUES('ozxdno',0,'','','','','',0,0,0,0,'',0,0);");
		return ok;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Invitations() {
		clear();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean exists(String code) {
		return invitations.get(code) != null;
	}
	public void clear() {
		if(invitations == null) {
			invitations = new HashMap<String,InvitationModel>();
		}
		invitations.clear();
	}
	public boolean load() {
		SQL sql = new SQL();
		sql.connect();
		if(!sql.isConnected()) {
			return false;
		}
		ResultSet set = sql.query("SELECT * FROM Invitations;");
		if(set == null) {
			String s = "CREATE TABLE Invitations(" + getSqlTableCols() + ");";
			if(sql.updata(s)) {
				return setSqlDefaultRows(sql);
			}
			return true;
		}
		try {
			while(set.next()) {
				InvitationModel i = getInvitation(set);
				invitations.put(i.getCode(), i);
			}
			return true;
		} catch(Exception e) {
			Global.CurrentError.show(29);
			return false;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
