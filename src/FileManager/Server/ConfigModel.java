package FileManager.Server;

public class ConfigModel {
	private String field;
	private String value;
	
	public String getField() {
		return field;
	}
	public String getValue() {
		return value;
	}
	
	public boolean setField(String field) {
		if(field == null) {
			return false;
		}
		this.field = field;
		return true;
	}
	public boolean setValue(String value) {
		if(value == null) {
			return false;
		}
		this.value = value;
		return true;
	}
	
	public ConfigModel() {
		clear();
	}
	public ConfigModel(String field, String value) {
		clear();
		setField(field);
		setValue(value);
	}
	
	public void clear() {
		field = "";
		value = "";
	}
}
