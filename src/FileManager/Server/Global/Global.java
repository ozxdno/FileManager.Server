package FileManager.Server.Global;

import FileManager.Server.Tools.*;
import FileManager.Server.Communication.*;
import FileManager.Server.Collection.*;
import FileManager.Server.Model.*;

public class Global {
	
	public final static Configs Configs = new Configs();
	public final static FileManager.Server.Error.Log ErrorLog = new FileManager.Server.Error.Log();
	public final static FileManager.Server.Error.Type ErrorType = new FileManager.Server.Error.Type();
	public final static ErrorModel CurrentError = new ErrorModel();
	public final static Files Files = new Files();
	public final static Folders Folders = new Folders();
	public final static Invitations Invitations = new Invitations();
	public final static Supports Supports = new Supports();
	public final static Users Users = new Users();
	
	public final static Server_CMD Server_CMD = new Server_CMD();
	public final static Server_TCP Server_TCP = new Server_TCP();
	public final static Server_UDP Server_UDP = new Server_UDP();
	
	public final static FixURL FixURL = new FixURL();
	public final static Time Time = new Time();
}
