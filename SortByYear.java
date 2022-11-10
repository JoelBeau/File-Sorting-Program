package sortPicsByYear;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import neededClasses.Picture;
import neededClasses.Sort;

/*
 * @author Joel Beauregard
 * SortPicsByYear.java
 * Dec 1, 2020 11:30:34 PM
 */
public class SortByYear implements Sort {

	private File orgPath;

	private String[] years = new String[12];

	// All pics into an array
	private File[] all;

	/**
	 *
	 * A constructor and sets the field of Org path in which the files are to be
	 * moved from Also invokes the create method, which creates the month folders in
	 * this case. Also invokes the set years
	 * 
	 * @see {@link #setOrgPath()}
	 * @see {@link #setYears()}
	 * @see {@link #create()}
	 * 
	 */

	public SortByYear() {
		this.orgPath = setOrgPath();
		this.years = setYears();
		this.all = this.orgPath.listFiles();
		create();
	}

	/**
	 * 
	 * @implNote implements it by creating the month folders in which the pictures
	 *           are to be moved accordingly
	 */
	@Override
	public void create() {

		for (int i = 0; i < years.length; i++) {
			File ff = new File(this.orgPath + "\\" + this.years[i]);
			ff.mkdir();
		}

	}

	/**
	 * 
	 * @implNote implements it by collecting the file path of the picture (HashMap
	 *           key) and the year in which it was last modified (HashMap value),
	 *           and putting those values into a HashMap
	 * 
	 * @return LinkedHashMap which contains the path name of the picture, and the
	 *         last modified month of it as the key
	 * 
	 * 
	 */
	@Override
	public HashMap<String, String> collect() {

		HashMap<String, String> pathsNMonths = new LinkedHashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		for (int i = 0; i < this.all.length; i++) {

			File cur = this.all[i];

			String modified = sdf.format(cur.lastModified());

			// Pictures object
			Picture p = new Picture(cur, modified);

			int index = Integer.parseInt(p.getLastModified().substring(6, 10));

			p.setYear(this.years[index - 2009]);

			pathsNMonths.put(p.getPath().toString(), p.getYear());

		}

		return pathsNMonths;

	}

	/**
	 * 
	 * @implNote implements it by using a for each loop that gets the entry set of
	 *           the HashMap and then gets the last index of \, this is to figure
	 *           out where to put the new folder, then moves it accordingly
	 * 
	 * @param HashMap, file path of the picture (HashMap key) and the month in which
	 *                 it was last modified (HashMap value)
	 * 
	 * @throws IOException if it can not find the file name, or if the file name is
	 *                     incorrect, or if the new file path is incorrect and not
	 *                     put together
	 * 
	 */
	@Override
	public void move(HashMap<String, String> pathsNMonths) throws IOException {

		for (Entry<String, String> e : pathsNMonths.entrySet()) {

			int last = e.getKey().lastIndexOf("\\") + 1;

			Files.move(Paths.get(e.getKey()),
					Paths.get(e.getKey().substring(0, last) + e.getValue() + "\\" + e.getKey().substring(last)));
		}

		System.out.println("Files moved successfully!");

	}

	// Gets the original path of the files
	public File getOrgPath() {
		return orgPath;
	}

	// Able to set original path if used again
	public void setOrgPath(File orgPath) {
		this.orgPath = orgPath;
	}

}