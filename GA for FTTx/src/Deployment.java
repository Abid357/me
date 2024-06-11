import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Deployment class
class Deployment {

	Area[] areas;
	double NPV;
	Map<Integer, Double> annualCashFlows;

	public Deployment() {
		NPV = 0;
		annualCashFlows = new HashMap<Integer, Double>();
		for (int i = 1; i <= Inputs.studyPeriod; i++)
			annualCashFlows.put(i, 0.0);
		Random rn = new Random();
		areas = new Area[Inputs.noOfAreas];
		for (int i = 0; i < Inputs.noOfAreas; i++) {
			areas[i] = new Area(Inputs.areas[i]);
			areas[i].MRPValue = rn.nextInt(Inputs.maxRolloutPeriod + 1);
		}
	}

	// Mutate
	void mutate() {
		Random rn = new Random();
		// Select a random mutation point
		int mutationPoint = rn.nextInt(areas.length);
		int mutationValue = rn.nextInt(Inputs.maxRolloutPeriod + 1);

		areas[mutationPoint].MRPValue = mutationValue;
	}

	void crossover(Deployment d) {
		Random rn = new Random();

		// Select a random crossover point
		int crossOverPoint = rn.nextInt(areas.length);

		// Swap values among parents
		for (int i = 0; i < crossOverPoint; i++) {
			Area area = new Area(areas[i]);
			areas[i] = new Area(d.areas[i]);
			d.areas[i] = new Area(area);
		}
	}

	// Calculate NPV
	public double calculateNetPresentValue() {
		for (int i = 0; i < Inputs.noOfAreas; i++)
			areas[i].calculatePresentValue(annualCashFlows);
		NPV = 0;
		for (int i = 1; i <= Inputs.studyPeriod; i++) {
			NPV += annualCashFlows.get(i) / Math.pow((1 + 0.01 * Inputs.interestRate), i);
		}
		System.out.println(NPV);
		return NPV;
	}

}