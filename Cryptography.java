
public class Cryptography {

	private static int ROWS = 0;
	private static int COLUMNS = 1;

	private static String decrypt(String ciphertext, int rowCount, int order) {
		char[] characters = ciphertext.toCharArray();
		int columnCount = (int) Math.ceil((double) characters.length / rowCount);
		char[][] matrix = new char[rowCount][columnCount];

		// fill up matrix based on order
		if (order == ROWS)
			for (int column = 0; column < columnCount; column++)
				for (int row = 0; row < rowCount; row++)
					matrix[row][column] = characters[(column * rowCount) + row];
		else
			for (int row = 0; row < rowCount; row++)
				for (int column = 0; column < columnCount; column++)
					matrix[row][column] = characters[(row * columnCount) + column];

		// remove from matrix into string
		String plainText = "";
		if (order == ROWS)
			for (int row = 0; row < rowCount; row++)
				for (int column = 0; column < columnCount; column++)
					plainText += matrix[row][column];
		else
			for (int column = 0; column < columnCount; column++)
				for (int row = 0; row < rowCount; row++)
					plainText += matrix[row][column];
		return plainText;
	}

	public static void main(String[] arg) {
		String cipherText = "HUZEEOPEIAWLTWFDAHITAGILERISLREACCYYROOOOIDMU";
		int noOfRows = 9;
		int order = COLUMNS;
		String plainText = decrypt(cipherText, noOfRows, order);
		System.out.println(plainText);
	}

}
