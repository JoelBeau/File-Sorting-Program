package neededClasses;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/*
 * @author Joel Beauregard
 * Sort.java
 * Dec 1, 2020 3:29:51 PM
 */
public interface Sort {

	/**
	 * @implSpec This method gets the user's input for where the orginal path of
	 *           the pictures are
	 * 
	 * @return File which denotes the original path for the pictures
	 */

	default File setOrgPath() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the file path please");
		File f = new File(in.nextLine());
		return f;
	}

	/**
	 * @implSpec Sets the years in which to sort the pictures by, used only in sort
	 *           by year and sort by year and month
	 * 
	 * @return A String array of the years of 2009 to 2020
	 * 
	 */

	default String[] setYears() {
		String[] years = new String[12];
		for (int i = 0, j = 2009; j <= 2022; i++, j++) {
			years[i] = Integer.toString(j);
		}
		return years;
	}

	/**
	 * 
	 * @implSpec could be creating folders for years, months or both. Method that
	 *           creates files in which to move the pictures to
	 */
	void create();

	/**
	 * 
	 * @implSpec the value of the HashMap could be the year it was last modified or
	 *           month, or both, depends on the way it's implemented Collects the
	 *           path name of the picture and is put into the HashMap as the value,
	 *           and gets the last modified
	 * 
	 * @return LinkedHashMap which contains the path name of the picture as the key,
	 *         and the last modified month, or year of it as the key
	 * 
	 */

	HashMap<String, String> collect();

	/**
	 * 
	 * 
	 * 
	 * @implSpec Moves the files in accordance with the HashMap, using the Path name
	 *           key from the HashMap, and the value which is to move the file to
	 * 
	 * @param HashMap pathsNmodified is from the previous method above, key is the
	 *                path name, and the value is used to determine where to move
	 *                the file in the method
	 * 
	 * @throws IOException if it can not find the file name, or if the file name is
	 *                     incorrect, or if the new file path is incorrect and not
	 *                     put together correctly
	 * 
	 */

	void move(HashMap<String, String> pathsNModfied) throws IOException;

}