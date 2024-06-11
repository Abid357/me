import java.util.Scanner;

public class Reflection {

	static int reflectionAcross(int[] coordinate) {
		int reflection = -1;
		if (coordinate[1] == -coordinate[3]) {
			if (coordinate[0] == -coordinate[2])
				reflection = 3;
			else
				reflection = 1;
		} else if (coordinate[0] == -coordinate[2]) {
			if (coordinate[1] == -coordinate[3])
				reflection = 3;
			else
				reflection = 2;
		}
		return reflection;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int T;
		Scanner reader = new Scanner(System.in);
		T = reader.nextInt();

		// INPUT
		int[][] input = new int[T][];
		for (int i = 0; i < T; i++) {
			input[i] = new int[4];
			input[i][0] = reader.nextInt();
			while (input[i][0] < -10000 || input[i][0] > 10000) {
				System.out.println("Value is between -10000 and 10000 only: ");
				input[i][0] = reader.nextInt();
			}
			input[i][1] = reader.nextInt();
			while (input[i][1] < -10000 || input[i][1] > 10000) {
				System.out.println("Value is between -10000 and 10000 only: ");
				input[i][1] = reader.nextInt();
			}
			input[i][2] = reader.nextInt();
			while (input[i][2] < -10000 || input[i][2] > 10000) {
				System.out.println("Value is between -10000 and 10000 only: ");
				input[i][2] = reader.nextInt();
			}
			input[i][3] = reader.nextInt();
			while (input[i][3] < -10000 || input[i][3] > 10000) {
				System.out.println("Value is between -10000 and 10000 only: ");
				input[i][3] = reader.nextInt();
			}
		}

		// OUTPUT
		System.out.println();
		for (int j = 0; j < T; j++) {
			switch (reflectionAcross(input[j])) {
			case -1:
				System.out.println("The points are not reflections.");
				break;
			case 1:
				System.out.println("The points are reflections across the x-axis.");
				break;
			case 2:
				System.out.println("The points are reflections across the y-axis.");
				break;
			case 3:
				System.out.println("The points are reflections across the origin.");
				break;
			}
		}
	}

}
