package FileManager.Server.Communication;

import FileManager.Server.Model.*;
import FileManager.Server.Global.*;

/**
 * 
 * @author ozxdno
 *
 * 00 - register : register = LOGIN_NAME | PASSWORD | NICK_NAME | EMAIL | PHONE | INVITATION
 *                 register = ERROR_CODE | USERMODEL
 *                 
 * 01 - login : login = LOGIN_NAME | PASSWORD
 *              login = ERROR_CODE | USERMODEL
 *              
 * 02 - browser : browser = USER | BROSWER_ARGS
 *                                 all folders
 *                                 folders
 *                                 files | FOLDER
 *                                 current
 *                                 users
 *                          USER = USERINFO - PARTID : id=ozxdno=password-partid
 *                browser = ERROR_CODE | PARTID | CONTENTS
 *                                               <ISROOT|URL>
 *                                               <ISROOT|URL>
 *                                               <URL>
 *                                               <URL>
 *                                               <URL>
 *                                       PARTID = 01 : Start Part;
 *                                       PARTID = 00 : End Part;
 *                                       
 * 03 - add folder : add folder = USER | FOLDER
 *                   add folder = ERROR_CODE
 *                   
 * 04 - del folder : del folder = USER | FOLDER
 *                   del folder = ERROR_CODE
 *                   
 * 05 - communication : communication = USER | close server
 *                                      USER | restart server
 *                                      USER | close idle
 *                                      USER | close client
 *                      communication = ERROR_CODE
 *                                      ERROR_CODE
 *                                      ERROR_CODE
 *                                      ERROR_CODE
 * 06 - synchro : synchro = USER | folders
 *                                 config
 *                                 files
 *                                 scores
 *                synchro = ERROR_CODE | PARTID | CONTENTS
 *                                               <ISROOT|URL>
 *                                               <FIELD|VALUE>
 *                                               <URL>
 *                                               <ID|SCORE>
 *                                      
 * 07 - forgot : forgot = LOGIN_NAME | PASSWORD | NICK_NAME | EMAIL | PHONE
 *               forgot = ERRORCODE | PARTID | LOGIN_NAME | PASSWORD | NICK_NAME | EMAIL | PHONE
 * 
 * 08 - invitation : invitation = USER | INVITATION
 *                   invitation = ERROR_CODE
 * 
 */
public class Server_CMD {
	public String deal(ConfigModel config) {
		if(config.getField().equals("register")) {
			return register(config);
		}
		if(config.getField().equals("login")) {
			return login(config);
		}
		
		return "wrong command = 1";
	}
	
	private String register(ConfigModel config) {
		if(config.getItemsSize() == 6) {
			return "register = 2";
		}
		String loginName = config.getString(0);
		String password = config.getString(1);
		String nickName = config.getString(2);
		String email = config.getString(3);
		String phone = config.getString(4);
		String invitation = config.getString(5);
		if(loginName.length() == 0) {
			return "register = 3";
		}
		if(password.length() == 0) {
			return "register = 4";
		}
		if(nickName.length() == 0) {
			//return "register = 5";
		}
		if(email.length() == 0) {
			//return "register = 6";
		}
		if(phone.length() == 0) {
			//return "register = 7";
		}
		if(invitation.length() == 0) {
			//return "register = 8";
		}
		
		if(Global.Users.exists(loginName)) {
			return "register = 9";
		}
		if(!Global.Invitations.exists(invitation)) {
			return "register = 10";
		}
		
		UserModel user = Global.Invitations.getUserModel(invitation);
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setNickName(nickName);
		user.setEmail(email);
		user.setPhone(phone);
		
		if(!user.register()) {
			return "register = " + Global.CurrentError.toString();
		}
		
		return "register = 0|" + user.toString();
	}
	private String login(ConfigModel config) {
		if(config.getItemsSize() != 2) {
			return "login = 11";
		}
		String loginName = config.getString(0);
		if(loginName == null || loginName.length() == 0) {
			return "login = 12";
		}
		String password = config.getString(1);
		if(password == null || password.length() == 0) {
			return "login = 13";
		}
		if(!Global.Users.exists(loginName)) {
			return "login = 14";
		}
		UserModel user = Global.Users.getUser(loginName, password);
		if(user == null) {
			return "login = 15";
		}
		
		return "login = 0|" + user.toString();
	}
}
