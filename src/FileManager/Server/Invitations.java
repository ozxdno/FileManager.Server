package FileManager.Server;

import java.util.*;

public class Invitations {
	private Map<String,InvitationModel> invitations = new HashMap<String,InvitationModel>();
	
	public Map<String,InvitationModel> getInvitations() {
		return invitations;
	}
	public InvitationModel getInvitation(String code) {
		return invitations.get(code);
	}
	public UserModel getUserModel(String code) {
		InvitationModel i = getInvitation(code);
		if(i == null) {
			return null;
		}
		return i.getUser();
	}
	
	public boolean setInvitation(InvitationModel invitation) {
		return invitation.register() == 0;
	}
}
