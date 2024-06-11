package lab4;

/**
 * A class that maintains information on a book. This might form part of a
 * larger application such as a library system, for instance.
 *
 * @author (Insert your name here.)
 * @version (Insert today's date here.)
 */
class Book {
	// The fields.
	private String author;
	private String title;
	private int pages;
	private String refNumber;

	/**
	 * Set the author and title fields when this object is constructed.
	 */
	public Book(String bookAuthor, String bookTitle, int bookPages) {
		author = bookAuthor;
		title = bookTitle;
		pages = bookPages;
		refNumber = "";
	}

	// accessor method
	public String getAuthor() {
		return author;
	}

	// accessor method
	public String getTitle() {
		return title;
	}

	// method
	public void printAuthor() {
		System.out.println("The author of the book is " + author + ".");
	}

	// method
	public void printTitle() {
		System.out.println("The title of the book is " + title + ".");
	}

	// accessor method
	public int getPages() {
		return pages;
	}

	// mutator method
	public void setRefNumber(String ref) {
		if (ref.length() < 3)
			System.out.println("Reference number is too short!");
		else
			refNumber = ref;
	}

	// accessor method
	public String getRefNumber() {
		return refNumber;
	}
	
	// main method
	public static void main(String[] args) {
		Book b = new Book("Asif Hasan", "HowToDotto", 246);
		// testing methods
		b.printAuthor();
		b.printTitle();
		
		// testing mutator method
		b.setRefNumber("66");
		b.setRefNumber("642");
		
		// testing accessor methods
		System.out.println(b.getAuthor());
		System.out.println(b.getTitle());
		System.out.println(b.getPages());
		System.out.println(b.getRefNumber());
	}
}