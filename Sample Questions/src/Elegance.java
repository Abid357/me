import java.util.Scanner;

public class Elegance {

	static String elegantTimeLog(int n)
	{
		String log = "";
		int hour;
		int minute;
		hour = n / 60;
		minute = n % 60;
		if (hour != 0)
			log += hour + " hour";
		if (hour > 1)
			log += "s";
		if (minute != 0)
			if (hour != 0)
				log += ", ";
			log += minute + " minute";
		if (minute > 1)
			log += "s";
		return log;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int T, n;
		Scanner reader = new Scanner(System.in);
		T = reader.nextInt();

		// INPUT
		int[] input = new int[T];
		for (int i = 0; i < T; i++) {		
			n = reader.nextInt();
			while (n < 1 || n > 100000) {
				System.out.println("Value is between 1 and 100000 only: ");
				n = reader.nextInt();
			}
			input[i] = n;
		}
		
		// OUTPUT
		System.out.println();
		for (int j = 0; j < T; j++)
			System.out.println(elegantTimeLog(input[j]));
	}
}
