package FileManager.Server.Model;

import FileManager.Server.Global.*;

public class ConfigModel {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String field;
	private String value;
	private String[] items;
	private boolean ok; 
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getField() {
		return field;
	}
	public String getValue() {
		return value;
	}
	public String[] getItems() {
		return items;
	}
	public boolean getIsOK() {
		return ok;
	}
	public int getItemsSize() {
		return items.length;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setLine(String line) {
		if(line == null || line.length() == 0) {
			return false;
		}
		line = Global.FixURL.fixLine(line);
		int idx = line.indexOf('=');
		if(idx < 0) {
			field = "";
			value = line;
			return true;
		}
		int cut1 = idx-1;
		int cut2 = idx+1;
		while(cut1 >= 0 && line.charAt(cut1) == ' ') {
			cut1--;
		}
		while(cut2 < line.length() && line.charAt(cut2) == ' ') {
			cut2++;
		}
		setField(line.substring(0, cut1+1));
		setValue(line.substring(cut2));
		return true;
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
		items = value.split("\\|");
		return true;
	}
	public boolean setItems(String[] items) {
		if(items == null) {
			return false;
		}
		this.items = items;
		value = "";
		if(items.length == 0) {
			return true;
		}
		for(int i=0; i<items.length; i++) {
			value += items[i] + "|";
		}
		value = value.substring(0, value.length());
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ConfigModel() {
		clear();
	}
	public ConfigModel(String line) {
		clear();
		setLine(line);
	}
	public ConfigModel(String field, String value) {
		clear();
		setField(field);
		setValue(value);
	}
	public ConfigModel(String field, String[] items) {
		clear();
		setField(field);
		setItems(items);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clear() {
		field = "";
		value = "";
		items = null;
		ok = true;
	}
	
	public boolean getBoolean() {
		try {
			ok = true;
			return Double.parseDouble(value) != 0;
		} catch(Exception e) {
			ok = false;
			return false;
		}
	}
	public int getInt() {
		try {
			ok = true;
			return Integer.parseInt(value);
		} catch(Exception e) {
			ok = false;
			return 0;
		}
	}
	public long getLong() {
		try {
			ok = true;
			return Long.parseLong(value);
		} catch(Exception e) {
			ok = false;
			return 0;
		}
	}
	public double getDouble() {
		try {
			ok = true;
			return Double.parseDouble(value);
		} catch(Exception e) {
			ok = false;
			return 0;
		}
	}
	public String getString() {
		ok = true;
		return value;
	}
	public boolean getBoolean(int item) {
		try {
			ok = true;
			return Double.parseDouble(items[item]) != 0;
		} catch(Exception e) {
			ok = false;
			return false;
		}
	}
	public int getInt(int item) {
		try {
			ok = true;
			return Integer.parseInt(items[item]);
		} catch(Exception e) {
			ok = false;
			return 0;
		}
	}
	public long getLong(int item) {
		try {
			ok = true;
			return Long.parseLong(items[item]);
		} catch(Exception e) {
			ok = false;
			return 0;
		}
	}
	public double getDouble(int item) {
		try {
			ok = true;
			return Double.parseDouble(items[item]);
		} catch(Exception e) {
			ok = false;
			return 0;
		}
	}
	public String getString(int item) {
		try {
			ok = true;
			return items[item];
		} catch(Exception e) {
			ok = false;
			return null;
		}
	}
	public boolean[] getBooleanList() {
		boolean[] list = new boolean[items.length];
		try {
			ok = true;
			for(int i=0; i<items.length; i++) {
				list[i] = Double.parseDouble(items[i]) != 0;
			}
			return list;
		} catch(Exception e) {
			ok = false;
			return null;
		}
	}
	public int[] getIntList() {
		int[] list = new int[items.length];
		try {
			ok = true;
			for(int i=0; i<items.length; i++) {
				list[i] = Integer.parseInt(items[i]);
			}
			return list;
		} catch(Exception e) {
			ok = false;
			return null;
		}
	}
	public long[] getLongList() {
		long[] list = new long[items.length];
		try {
			ok = true;
			for(int i=0; i<items.length; i++) {
				list[i] = Long.parseLong(items[i]);
			}
			return list;
		} catch(Exception e) {
			ok = false;
			return null;
		}
	}
	public double[] getDoubleList() {
		double[] list = new double[items.length];
		try {
			ok = true;
			for(int i=0; i<items.length; i++) {
				list[i] = Double.parseDouble(items[i]);
			}
			return list;
		} catch(Exception e) {
			ok = false;
			return null;
		}
	}
	public String[] getStringList() {
		ok = true;
		return items;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
