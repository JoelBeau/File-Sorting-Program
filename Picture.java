package neededClasses;

import java.io.File;

/*
 * @author Joel Beauregard
 * Picture.java
 */
public class Picture {

	//Place where picture is located
	private File path;
	
	//Total time when the file was last modified
	private String lastModified;
	
	//Month in which it was last modified
	private String month;
	
	//Year in which it was last modified
	private String year;
	
	public Picture(File path, String lastModified) {
		setPath(path);
		setLastModified(lastModified);
	}

	//Getters and setters for all variables

	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
