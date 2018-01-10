package FileManager.Server.Tools;

public class FixURL {
	public String getPath(String url) {
		if(url == null || url.length() == 0) {
			return "";
		}
		int idx = url.lastIndexOf('\\');
		if(idx < 0) {
			return "";
		}
		return url.substring(0,idx-1);
	}
	public String getName(String url) {
		if(url == null || url.length() == 0) {
			return "";
		}
		int idx = url.lastIndexOf('\\');
		if(idx < 0) {
			return "";
		}
		return url.substring(idx+1, url.length()-1);
	}
	public String getNameWithoutExtension(String url) {
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
	public String getExtension(String url) {
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
	public String fixExtension(String extension) {
		if(extension == null || extension.length() == 0) {
			return "";
		}
		extension = extension.toLowerCase();
		if(extension.charAt(0) != '.') {
			extension = '.' + extension;
		}
		return extension;
	}
	public String fixLine(String line) {
		if(line == null || line.length() == 0) {
			return "";
		}
		int cut0 = 0;
		while(cut0 < line.length() && line.charAt(cut0) != '\r' && line.charAt(cut0) != '\n' && line.charAt(cut0) != 0) {
			cut0++;
		}
		return line.substring(0, cut0);
	}
}
