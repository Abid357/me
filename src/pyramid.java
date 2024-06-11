import java.util.InputMismatchException;
import java.util.Scanner;

public class pyramid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner read = new Scanner(System.in);
		int k = 0, d;
		int[][] p = null;
		int v;
		d = read.nextInt();
		while (d != -1) {
			p = null;
			p = new int[d][];
			for (int i = 0; i < d; i++) {
				p[i] = new int[i + 1];
				for (int j = 0; j < i + 1; j++) {
					try {
						v = read.nextInt();
					} catch (InputMismatchException e) {
						continue;
					}
					p[i][j] = v;
				}
			}
		}
	}
}
