package sortPicsByMonth;

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
 * SortByMonth.java 
 * Nov 25, 2020 7:57:19 PM
 */

public class SortByMonth implements Sort {

	private File orgPath;

	private String[] months = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	// All pics into an array
	private File[] all;

	/**
	 * 
	 * A constructor that sets the field of Org path which is the files that are to
	 * be moved from Also invokes the create method, which creates the month folders
	 * in this case.
	 * 
	 * @see {@link #setOrgPath()}
	 * @see {@link #create()}
	 */

	public SortByMonth() {
		this.orgPath = setOrgPath();
		this.all = this.orgPath.listFiles();
		create();
	}

	/**
	 * 
	 * @implNote implements it by creating the month folders in which the pictures
	 *           are to be moved accordingly. Uses the class variable, months to
	 *           create the folders
	 * 
	 */
	@Override
	public void create() {

		for (int i = 0; i < months.length; i++) {
			File ff = new File(this.orgPath + "\\" + this.months[i]);
			ff.mkdir();
		}

	}

	/**
	 * 
	 * @implNote implements it by collecting the file path of the picture (HashMap
	 *           key) and the month in which it was last modified (HashMap value),
	 *           and putting those values into a HashMap
	 * 
	 * @return LinkedHashMap which contains the path name of the picture as the key,
	 *         and the last modified month of it as the key
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

			int index = Integer.parseInt(p.getLastModified().substring(0, 2));

			p.setMonth(months[index - 1]);

			pathsNMonths.put(p.getPath().toString(), p.getMonth());

		}

		return pathsNMonths;

	}

	/**
	 * 
	 * @implNote implements it by using a for each loop that gets the entry set of
	 *           the HashMap and then gets the last index of \, this is to figure
	 *           out where to put the new folder, then moves it accordingly
	 * 
	 * @param HashMap, file path of the picture (HashMap key) and the month and in
	 *                 which it was last modified (HashMap value)
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