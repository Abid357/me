import java.util.Scanner;

public class Skroutz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// INPUT
		int T, N, B, input;
		Scanner reader = new Scanner(System.in);
		T = reader.nextInt();
		int[][] output = new int[T][];
		for (int i = 0; i < T; i++) {
			N = reader.nextInt();
			while (N < 1 || N > 100) {
				System.out.print("N is between 1 and 100 only: ");
				N = reader.nextInt();
			}
			B = reader.nextInt();
			while (B < 1 || B > 32) {
				System.out.print("B is between 1 and 32 only: ");
				B = reader.nextInt();
			}
			output[i] = new int[((N * B) / 32) + 1];
			for (int j = 0; j < N; j++) {
				input = reader.nextInt();
				input = input << (B * j) % 32;
				output[i][(B * j) / 32] = output[i][(B * j) / 32] | input;
			}
		}

		// OUTPUT
		System.out.println();
		for (int i = 0; i < T; i++) {
			for (int j = 0; j < output[i].length; j++) {
				System.out.print(output[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
