import java.util.Scanner;

public class Explosion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N;
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		while (N < 1 || N > 100) {
			System.out.print("N is between 1 and 100 only: ");
			N = reader.nextInt();
		}
		int[] T = new int[N];
		for (int i = 0; i < N; i++) {
			T[i] = reader.nextInt();
			while (T[i] < 0 || T[i] > 1000) {
				System.out.print("T is between 0 and 1000 only: ");
				T[i] = reader.nextInt();
			}
		}
		System.out.println();
		for (int j = 0; j < N; j++) {
			System.out.println(T[j] * T[j] + (T[j] + 1) * (T[j] + 1));
		}
	}
}
