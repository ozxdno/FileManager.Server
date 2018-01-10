package FileManager.Server.Collection;

import java.util.*;
import FileManager.Server.Model.*;

public class Configs {
	private Set<ConfigModel> configs;
	
	public Set<ConfigModel> getConfigs() {
		return configs;
	}
	public ConfigModel getConfig(String field) {
		Iterator<ConfigModel> it = configs.iterator();
		while(it.hasNext()) {
			ConfigModel c = it.next();
			if(c.getField().equals(field)) {
				it.remove();
				return c;
			}
		}
		return null;
	}
	public ConfigModel getNext() {
		Iterator<ConfigModel> it = configs.iterator();
		if(it.hasNext()) {
			ConfigModel c = it.next();
			it.remove();
			return c;
		}
		return null;
	}
	
	public Configs() {
		clear();
	}
	
	public void clear() {
		if(configs == null) {
			configs = new HashSet<ConfigModel>();
		}
		configs.clear();
	}
	public boolean load() {
		return false;
	}
	public boolean exists(String field) {
		return getConfig(field) != null;
	}
	public boolean addConfig(ConfigModel config) {
		return configs.add(config);
	}
	public boolean isEmpty() {
		return configs.isEmpty();
	}
	public int size() {
		return configs.size();
	}
}
