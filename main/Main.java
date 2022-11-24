package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import sortPicsByMonth.SortByMonth;
import sortPicsByYear.SortByYear;
import sortPicsByYearNMonth.SortByBoth;

/*
 * @author Joel Beauregard
 * Main.java
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		SortByMonth sm = null;
		SortByYear sy = null;
		SortByBoth sb = null;

		HashMap<String, String> pathsNModified = null;

		Scanner in = new Scanner(System.in);

		System.out.println("What would you like to sort by, month, Year, or both?");
		String toSort = in.nextLine().toLowerCase();

		switch (toSort) {

		case "month":
			sm = new SortByMonth();
			pathsNModified = sm.collect();
			sm.move(pathsNModified);
			break;

		case "year":
			sy = new SortByYear();
			pathsNModified = sy.collect();
			sy.move(pathsNModified);
			break;

		case "both":
			sb = new SortByBoth();
			pathsNModified = sb.collect();
			sb.move(pathsNModified);
			break;

		}
		
		in.close();

	}

}