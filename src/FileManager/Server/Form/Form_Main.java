package FileManager.Server.Form;

import FileManager.Server.Global.*;

public class Form_Main {
	public static void main(String[] args) {		
		Global.ErrorType.load();
		Global.Configs.load();
		Global.Files.load();
		Global.Folders.load();
		Global.Users.load();
		Global.Invitations.load();
		Global.Supports.load();
		
		Global.Server_TCP.start();
	}
}

