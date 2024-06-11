import java.util.Scanner;

public class MatrixMadness {

	public static void main(String[] args) {
		int[][] matrix = new int[4][5];
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 5; col++)
				matrix[row][col] = row + col + 1;

		int N;
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		while (N < 1 || N > 100) {
			System.out.print("N is between 1 and 100 only: ");
			N = reader.nextInt();
		}

		// INPUT
		int[][] input = new int[N][];
		for (int i = 0; i < N; i++) {
			input[i] = new int[2];
			input[i][0] = reader.nextInt();
			while (input[i][0] < 1 || input[i][0] > 100) {
				System.out.print("R and C are between 1 and 100 only: ");
				input[i][0] = reader.nextInt();
			}
			input[i][1] = reader.nextInt();
			while (input[i][1] < 1 || input[i][1] > 100) {
				System.out.print("R and C are between 1 and 100 only: ");
				input[i][1] = reader.nextInt();
			}
		}

		// OUTPUT
		System.out.println();
		int sum = 0, row2, col2;
		for (int k = 0; k < N; k++) {
			row2 = input[k][0];
			col2 = input[k][1];
			sum = 0;
			for (int l = 0; l < row2; l++) {
				for (int m = 0; m < col2; m++) {
					sum += matrix[l][m];
				}
			}
			System.out.println(sum);
		}
	}
}
