import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * Code supplied for the CO551 assignment 2.
 * 
 * @author Anna Jordanous
 *
 */
public class SCDataFromCSV {

	private String[][] data;

	public SCDataFromCSV(String filename) {
		try {
			CSVReader reader = new CSVReader(new FileReader(filename));
			List<String[]> contents = reader.readAll();
			reader.close();
			data = contents.toArray(new String[0][0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get a copy of all the data, in an String[][] array form
	 * 
	 * @return String[][] a copy of the data
	 */
	String[][] getData() {
		return data;
	}

	/**
	 * Get all the data in a particular position of the String[][] array
	 * 
	 * @param pos
	 *            the position in the array for which we want the user data
	 * @return a String array containing [userName, TrackCount, FollowersCount]
	 *         for the user at the ith position in the array
	 */
	String[] getDataAt(int pos) {
		return data[pos];
	}

	/**
	 * Get the userName of the user in a particular position of the String[][]
	 * array
	 * 
	 * @param pos
	 *            the position in the array for which we want the user data
	 * @return a String representation of the userName for the user at the ith
	 *         position in the array
	 */
	String getUserNameAt(int pos) {
		return data[pos][0];
	}

	/**
	 * Get the number of tracks that has been uploaded by a user in a particular
	 * position of the String[][] array
	 * 
	 * @param pos
	 *            the position in the array for which we want the user data
	 * @return a String representation of the number of tracks uploaded by the
	 *         user at the ith position in the array
	 */
	String getTrackCountAt(int pos) {
		return data[pos][1];
	}

	/**
	 * Get the number of followers a particular user has, for a user in a
	 * particular position of the String[][] array
	 * 
	 * @param pos
	 *            the position in the array for which we want the user data
	 * @return a String representation of the number of followers for the user
	 *         at the ith position in the array
	 */
	String getFollowersCountAt(int pos) {
		return data[pos][2];
	}

	/**
	 * Return the number of users in our data
	 * 
	 * @return the number of users in the data
	 */
	int getSize() {
		return data.length;
	}

	/**
	 * Display a user's details in a String format For display purposes only
	 * 
	 * @param pos
	 *            the position in the array of the user we are interested in
	 * @return a String representation of the data for a particular user
	 */
	String viewUserDetails(int pos) {
		String returnString = "The user with username " + getUserNameAt(pos);
		returnString += " has " + getTrackCountAt(pos) + " tracks listed on SoundCloud, ";
		returnString += "and " + getFollowersCountAt(pos) + " followers on SoundCloud";
		return returnString;
	}

	/**
	 * Display the entire contents of the data in a String format that can be
	 * viewed more easily
	 */
	public String toString() {
		String returnString = "";
		for (int i = 0; i < getSize(); i++) {
			String[] datum = getDataAt(i);
			for (String s : datum) {
				returnString += s + " ";
			}
			returnString += "\n";
		}
		returnString += "Total: " + getSize() + " items";
		return returnString;
	}

	void swap(int pos1, int pos2) {
		String[] temp = data[pos1];
		data[pos1] = data[pos2];
		data[pos2] = temp;
	}

	/**
	 * Sort data in descending order of number of tracks using Bubble Sort
	 * algorithm
	 */
	void bubbleSortByTrack() {
		for (int j = 1; j < getSize(); j++) // iterate through data records for
											// n - 1 times where n = size of
											// array of records
			for (int i = 0; i < getSize() - j; i++) { // traverse through data
														// records comparing ith
														// record with (i+1)th
														// record
				if (Integer.parseInt(getTrackCountAt(i)) < Integer.parseInt(getTrackCountAt(i + 1))) { // if
																										// ith
																										// record
																										// is
																										// smaller
																										// than
																										// (i+1)th
																										// record
					swap(i, i + 1); // swap ith and (i+1)th record
				}
			}
	}

	/**
	 * Sort data in ascending order of number of followers using a sorting
	 * algorithm
	 */
	void sortByFollowers() {
		int min; // variable to store index of minimum follower count
		for (int j = 0; j < getSize() - 1; j++) { // iterate through data
													// records for
													// n - 1 times where n =
													// size of
													// array of records
			min = j; // store the current primary loop index value as minimum
			for (int i = j + 1; i < getSize(); i++) // traverse through data
													// records comparing ith
													// record with (i+1)th
													// record
			{
				if (Integer.parseInt(getFollowersCountAt(min)) > Integer.parseInt(getFollowersCountAt(i)))
					min = i;
			}
			swap(min, j);
		}
	}

	/*
	 * Helper function for linear recursive search to keep track of position of
	 * index pointer
	 */
	String[] helperLinearRecursiveSearch(String searchString, int pos) {
		if (pos == getSize())
			return null;
		if (data[pos][0].equals(searchString))
			return data[pos];
		else
			return helperLinearRecursiveSearch(searchString, ++pos);
	}

	/**
	 * Search data recursively based on the passed username string
	 */
	String[] LinearRecursiveSearch(String searchString) {
		return helperLinearRecursiveSearch(searchString, 0);
	}
}
