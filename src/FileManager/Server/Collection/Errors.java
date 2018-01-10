package FileManager.Server.Collection;

import FileManager.Server.Model.*;
import java.util.*;

public class Errors {
	private ArrayList<ErrorModel> errors;
	
	public Errors() {
		
	}
	
	public void clear() {
		if(errors == null) {
			errors = new ArrayList<ErrorModel>();
		}
		errors.clear();
	}
}
