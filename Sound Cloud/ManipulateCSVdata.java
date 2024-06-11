/**
 * Code to manipulate the SoundCloud data supplied. For the CO551 second
 * assessment for 2016/17
 * 
 * @author Add your user name here
 * @author Anna Jordanous where indicated
 *
 */
public class ManipulateCSVdata {

	/**
	 * Main method - to demonstrate the code as supplied for the assessment.
	 * Create a new SCDataFromCSV object scData (see SCDataFromCSV.java),
	 * containing the data in the supplied SoundCloudUsers1000.csv file.
	 * Demonstrate the toString method for SCDataFromCSV objects, to display the
	 * data on standard output Demonstrate the viewUserDetails method for
	 * SCDataFromCSV objects, to view the data in a particular posiiton in the
	 * SCData array
	 * 
	 * @param args
	 *            ignore: standard requirement for main methods in Java
	 */
	public static void main(String[] args) {
		SCDataFromCSV scData = new SCDataFromCSV("data/SoundCloudUsers1000.csv");
		System.out.println("========================\nSound Cloud User Details\n========================\n");
		System.out.println(scData.toString());

		scData.bubbleSortByTrack();
		System.out.println("\n==============\nBUBBLE SORTING\n==============\n");
		System.out.println(scData.toString());

		String[] searchedResult = scData.LinearRecursiveSearch("Eric");
		System.out.println("\n=======================\nLINEAR RECURSIVE SEARCH\n=======================\n");
		System.out.println(searchedResult[0] + " " + searchedResult[1] + "  " + searchedResult[2]);

		scData.sortByFollowers();
		System.out.println("\n=================\nSELECTION SORTING\n=================\n");
		System.out.println(scData.toString());
		
		System.out.println("\n=================\nView User Details\n=================\n");
		System.out.println(scData.viewUserDetails(scData.getSize() - 1)); // print
																			// the
																			// details
																			// of
																			// the
																			// last
																			// user
																			// in
																			// scData
	}
}
