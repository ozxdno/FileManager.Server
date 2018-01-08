package FileManager.Server;

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
public class Command {
	public static final String deal(ConfigModel config) {
		if(config.getField().equals("login")) {
			return login(config);
		}
		
		return "wrong command = 0x010101";
	}
	
	private static final String register(ConfigModel config) {
		if(config.getItemsSize() == 6) {
			return "register = 0x";
		}
		
		return "";
	}
	private static final String login(ConfigModel config) {
		if(config.getItemsSize() != 2) {
			return "login = 0x010106";
		}
		String loginName = config.getString(0);
		if(loginName == null || loginName.length() == 0) {
			return "login = 0x010102";
		}
		String password = config.getString(1);
		if(password == null || password.length() == 0) {
			return "login = 0x010103";
		}
		if(!Data.users.exists(loginName)) {
			return "login = 0x010104";
		}
		UserModel user = Data.users.getUser(loginName, password);
		if(user == null) {
			return "login = 0x010105";
		}
		
		return "login = 0x000000|" + user.toString();
	}
}
