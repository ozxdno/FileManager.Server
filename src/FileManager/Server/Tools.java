package FileManager.Server;

public final class Tools {
	public final static String getPath(String url) {
		if(url == null || url.length() == 0) {
			return "";
		}
		int idx = url.lastIndexOf('\\');
		if(idx < 0) {
			return "";
		}
		return url.substring(0,idx-1);
	}
	public final static String getName(String url) {
		if(url == null || url.length() == 0) {
			return "";
		}
		int idx = url.lastIndexOf('\\');
		if(idx < 0) {
			return "";
		}
		return url.substring(idx+1, url.length()-1);
	}
	public final static String getNameWithoutExtension(String url) {
		if(url == null || url.length() == 0) {
			return "";
		}
		int bg = url.lastIndexOf('\\');
		int ed = url.lastIndexOf('.');
		if(bg < 0) {
			bg = -1;
		}
		if(ed < 0 || ed < bg) {
			ed = url.length();
		}
		return url.substring(bg+1, ed-1);
	}
	public final static String getExtension(String url) {
		if(url == null || url.length() == 0) {
			return "";
		}
		int bg = url.lastIndexOf('\\');
		int ed = url.lastIndexOf('.');
		if(bg < 0) {
			bg = -1;
		}
		if(ed < 0 || ed < bg) {
			return "";
		}
		return url.substring(ed+1, url.length()-1);
	}
	public final static String fixExtension(String extension) {
		if(extension == null || extension.length() == 0) {
			return "";
		}
		extension = extension.toLowerCase();
		if(extension.charAt(0) != '.') {
			extension = '.' + extension;
		}
		return extension;
	}
}
