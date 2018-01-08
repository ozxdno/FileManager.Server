package FileManager.Server;

/**
 * 
 * @author ozxdno
 *
 * 00 - register : register = LOGIN_NAME | PASSWORD | NICK_NAME | EMAIL | PHONE
 *                 register = ERROR_CODE
 *                 
 * 01 - login : login = LOGIN_NAME | PASSWORD
 *              login = ERROR_CODE
 *              
 * 02 - broswer : broswer = USER | BROSWER_ARGS
 *                                 all folders
 *                                 folders
 *                                 files | FOLDER
 *                                 current
 *                                 users
 *                          USER = USERINFO - PARTID : id=ozxdno=password-partid
 *                broswer = ERROR_CODE | PARTID | CONTENTS
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
 */
public class Command {
	public static final String deal(ConfigModel config) {
		if(config.getValue().equals("login")) {
			return login(config);
		}
		
		return "wrong command = 0x010101";
	}
	
	private static final String login(ConfigModel config) {
		String loginName = config.getString(0);
		if(loginName == null || loginName.length() == 0) {
			return "login = 0x010102";
		}
		String password = config.getString(1);
		if(password == null || password.length() == 0) {
			return "login = 0x010103";
		}
		
		return "login = 0x000000";
	}
}
