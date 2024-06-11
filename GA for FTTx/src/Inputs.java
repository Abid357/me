import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Inputs {

	static int runs;
	static int noOfAreas;
	static int studyPeriod;
	static double annualRental;
	static double CAPEX;
	static double OPEX;
	static double interestRate;
	static int maxRolloutPeriod;
	static Area[] areas;

	public static boolean initVariables() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("assignment2a.csv"));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				if (tokens[0].contains("Number of areas"))
					noOfAreas = Integer.parseInt(tokens[1]);
				else if (tokens[0].contains("Study period"))
					studyPeriod = Integer.parseInt(tokens[1]);
				else if (tokens[0].contains("Annual rental charges"))
					annualRental = Double.parseDouble(tokens[1]);
				else if (tokens[0].contains("CAPEX"))
					CAPEX = Double.parseDouble(tokens[1]);
				else if (tokens[0].contains("OPEX"))
					OPEX = Double.parseDouble(tokens[1]);
				else if (tokens[0].contains("Interest rate"))
					interestRate = Double.parseDouble(tokens[1]);
				else if (tokens[0].contains("Maximum rollout period"))
					maxRolloutPeriod = Integer.parseInt(tokens[1]);
			}
			br.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean initAreas() {
		areas = new Area[noOfAreas];
		try {
			BufferedReader br = new BufferedReader(new FileReader("assignment2b.csv"));
			String line = br.readLine();
			int i = 0;
			if (line != null)
				while ((line = br.readLine()) != null) {
					String[] tokens = line.split(",");
					int population = Integer.parseInt(tokens[0]);
					double adoptionRate = Double.parseDouble(tokens[1]);
					areas[i] = new Area(population, adoptionRate);
					i++;
				}
			br.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void writeData(int run, double fitness) {
		try {
			BufferedWriter bw = null;
			File file = new File("results.csv");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));

			bw.write("Run " + run + "," + fitness + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static double getMean(double[] data) {
		double sum = 0;
		for (double d : data)
			sum += d;
		return sum / data.length;
	}

	static double getVariance(double[] data) {
		double mean = getMean(data);
		double temp = 0;
		for (double d : data)
			temp += (d - mean) * (d - mean);
		return temp / (data.length - 1);
	}

	static double getStdDev(double[] data) {
		return Math.sqrt(getVariance(data));
	}

	static double getMin(double[] data) {
		if (data.length > 0) {
			double min = data[0];
			for (int i = 1; i < data.length; i++)
				if (data[i] < min)
					min = data[i];
			return min;
		} else
			return -1;
	}

	static double getMax(double[] data) {
		if (data.length > 0) {
			double max = data[0];
			for (int i = 1; i < data.length; i++)
				if (data[i] > max)
					max = data[i];
			return max;
		} else
			return -1;
	}

	static void writeStatistics() {
		double[] data = new double[runs];
		try {
			BufferedReader br = new BufferedReader(new FileReader("results.csv"));
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				data[i] = Double.parseDouble(tokens[1]);
				i++;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			BufferedWriter bw = null;
			File file = new File("results.csv");

			// true = append file
			bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));

			bw.write(getMean(data) + "\n" + getStdDev(data) + "\n" + getMax(data) + "\n" + getMin(data) + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		runs = 1;

		File file = new File("results.csv");
		if (file.exists())
			file.delete();

		if (initVariables())
			if (initAreas()) {
				FTTx demo = new FTTx(10000, 750, 0.8, 0.2, 4);
				// Run GA for 10 times
				for (int i = 0; i < runs; i++) {
					demo.simulate();
				}
				writeStatistics();
			}
	}
}