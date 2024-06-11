import java.util.Scanner;

public class JosephusReturns {

	static int getSurvivingPosition(int[] people, int kill) {
		int count = people.length;
		int skip = kill - 1;
		int skipcount;
		kill--;
		while (count > 1) {
			people[kill] = 0;
			count--;
			skip++;
			skipcount = skip;
			kill = (kill + 1) % people.length;
			while (skipcount != 0 || people[kill] != 1) {
				if (people[kill] == 1) {
					skipcount--;
					kill = (kill + 1) % people.length;
				} else
					kill = (kill + 1) % people.length;
			}
		}

		int i = 0;
		while (people[i] != 1) {
			i++;
		}
		return i + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int T, N, D;
		Scanner reader = new Scanner(System.in);
		T = reader.nextInt();

		// INPUT
		int[][] input = new int[T][];
		for (int i = 0; i < T; i++) {
			N = reader.nextInt();
			while (N < 0 || N > 1000) {
				System.out.println("N is between 0 and 1000 only: ");
				N = reader.nextInt();
			}
			D = reader.nextInt();
			while (D < 0 || D > 1000) {
				System.out.println("D is between 0 and 1000000 only: ");
				D = reader.nextInt();
			}
			input[i] = new int[2];
			input[i][0] = N;
			input[i][1] = D;
		}

		// OUTPUT
		System.out.println();
		int kill;
		int[] people;
		for (int j = 0; j < T; j++) {
			people = new int[input[j][0]];
			for (int k = 0; k < people.length; k++)
				people[k] = 1;
			kill = input[j][1];
			System.out.println(getSurvivingPosition(people, kill));
		}
	}
}
