package FileManager.Server;

public final class Tools {
	public final static String getPath(String url) {
		return "";
	}
	public final static String getName(String url) {
		return "";
	}
	public final static String getNameWithoutExtension(String url) {
		return "";
	}
	public final static String getExtension(String url) {
		return "";
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
