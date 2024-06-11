import java.util.Scanner;

public class COE371 {

	public static int ROW = 9;

	// QUESTION 2
//	public static String TABLE = "192.168.10.10/24 192.168.10.1 192.168.10.254 192.168.10.255\n" +
//			"10.101.99.17/23 10.101.98.1 10.101.99.254 10.101.99.255\n" +
//			"209.165.200.227/27 209.165.200.225 209.165.200.254 209.165.200.255\n" +
//			"172.31.45.252/24 172.31.45.1 172.31.45.254 172.31.45.255\n" +
//			"10.1.8.200/26 10.1.8.193 10.1.8.254 10.1.8.255\n" +
//			"172.16.117.77/20 172.16.112.1 172.16.127.254 172.16.127.255\n" +
//			"10.1.1.101/25 10.1.1.1 10.1.1.126 10.1.1.127\n" +
//			"209.165.202.140/27 209.165.202.129 209.165.202.158 209.165.202.159\n" +
//			"192.168.28.45/28 192.168.28.33 192.168.28.46 192.168.28.47";

	// QUESTION 1
	public static String TABLE = "192.168.10.10/24 N.N.N.H 255.255.255.0 192.168.10.0\n" +
			"10.101.99.17/23 N.N.nnnnnnnh.H 255.255.254.0 10.101.98.0\n" +
			"209.165.200.227/27 N.N.N.nnnhhhhh 255.255.255.224 209.165.200.224\n" +
			"172.31.45.252/24 N.N.N.H 255.255.255.0 172.31.45.0\n" +
			"10.1.8.200/26 N.N.N.nnhhhhhh 255.255.255.192 10.1.8.192\n" +
			"172.16.117.77/20 N.N.nnnnhhhh.H 255.255.240.0 172.16.112.0\n" +
			"10.1.1.101/25 N.N.N.nhhhhhhh 255.255.255.128 10.1.1.0\n" +
			"209.165.202.140/27 N.N.N.nnnhhhhh 255.255.255.224 209.165.202.128\n" +
			"192.168.28.45/28 N.N.N.nnnnhhhh 255.255.255.240 192.168.28.32";

	public static void correct(String[][] data, String[][] sol) {
		// check if number of columns match for all rows
		for (int r = 0; r < ROW; ++r)
			if (data[r].length != sol[r].length)
				System.out.println("COLUMNS DO NOT MATCH!");

		for (int r = 0; r < ROW; ++r) {
			for (int c = 0; c < sol[0].length; ++c) {
				if (!data[r][c].equals(sol[r][c]))
					System.out.println("ROW = " + (r + 1) + "\tCOL = " + (c + 1));
			}
		}
	}

	public static void print(String[][] sol){
		for (int r = 0; r < ROW; ++r) {
			for (int c = 0; c < sol[0].length; ++c)
				System.out.print("|" + sol[r][c] + "|");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// input data
		Scanner reader = new Scanner(System.in);
		String line = null;
		int r = 0;
		String[][] data = new String[ROW][];
		while(reader.hasNextLine()) {
			line = reader.nextLine();
			if (line.equals("\r\n") || line.equals(""))
				continue;
			else if (line.equals("-1"))
				break;
			String[] tokens = line.split("\t");
			data[r++] = tokens;
		}
		reader.close();

		// convert solution
		String[][] sol = new String[ROW][];
		String[] lines = TABLE.split("\n");
		for (int i = 0; i < ROW; ++i) {
			String[] tokens = lines[i].split(" ");
			sol[i] = tokens;
		}

		// trim all strings
		for (int i = 0; i < ROW; ++i)
			for (int j = 0; j < data[i].length; ++j)
				data[i][j] = data[i][j].trim();
		for (int i = 0; i < ROW; ++i)
			for (int j = 0; j < sol[i].length; ++j)
				sol[i][j] = sol[i][j].trim();

		print(sol);
		System.out.println("===============================================");
		print(data);
		correct(data, sol);
	}

}
