import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//FTTx class
class FTTx {

	private List<Deployment> deployments;
	private int populationCount;
	private int generationCount;
	private double mutationProb;
	private double crossOverProb;
	private int tournamentSize;

	public FTTx(int populationCount, int generationCount, double mutationProb,
			double crossOverProb, int tournamentSize) {
		this.populationCount = populationCount;
		this.generationCount = generationCount;
		this.mutationProb = mutationProb;
		this.crossOverProb = crossOverProb;
		this.tournamentSize = tournamentSize;
		deployments = new ArrayList<Deployment>(this.populationCount);
		for (int i = 0; i < this.populationCount; i++) {
			deployments.add(new Deployment());
		}
	}

	// Simulate the GA model
	public void simulate() {
		List<Deployment> nextGeneration = new ArrayList<>();
		for (int i = 0; i < generationCount; i++) {
			while (nextGeneration.size() < populationCount) {
				mutation(nextGeneration);
				crossover(nextGeneration);
			}
			deployments = nextGeneration.subList(0, populationCount - 1);
			calculateFitness();
			double maxFit = getFittest().NPV;
		//	print(i, maxFit);
			Inputs.writeData(i + 1, maxFit);
		}
	}

	// Get the fittest deployment
	public Deployment getFittest() {
		Deployment d = deployments.get(0);
		double maxFit = deployments.get(0).NPV;
		for (int i = 1; i < deployments.size(); i++)
			if (maxFit < deployments.get(i).NPV) {
				maxFit = deployments.get(i).NPV;
				d = deployments.get(i);
			}
		return d;
	}
	//
	// // Get the second most fittest deployment
	// public Deployment getSecondFittest() {
	// Deployment d = deployments.get(0);
	// double maxFit1 = deployments.get(0).NPV;
	// double maxFit2 = deployments.get(0).NPV;
	// int indexFit1 = 0;
	// for (int i = 1; i < deployments.length; i++)
	// if (deployments.get(i).NPV > maxFit1) {
	// maxFit2 = maxFit1;
	// d = deployments[indexFit1];
	// maxFit1 = deployments.get(i).NPV;
	// indexFit1 = i;
	// } else if (deployments.get(i).NPV > maxFit2) {
	// maxFit2 = deployments.get(i).NPV;
	// d = deployments.get(i);
	// }
	// secondFittest = d;
	// return secondFittest;
	// }
	//
	// // Get index of least fittest deployment
	// public int getLeastFittestIndex() {
	// int index = 0;
	// double minFit = deployments.get(0).NPV;
	// for (int i = 1; i < deployments.length; i++)
	// if (minFit > deployments.get(i).NPV) {
	// minFit = deployments.get(i).NPV;
	// index = i;
	// }
	// return index;
	// }

	// Calculate NPV of each deployment
	public void calculateFitness() {
		for (int i = 0; i < deployments.size(); i++) {
			deployments.get(i).calculateNetPresentValue();
		}
	}

	// Tournament selection
	public Deployment tournamentSelection() {
		Deployment bestDeployment = null;
		Random rn = new Random();
		for (int i = 0; i < tournamentSize; i++) {
			Deployment d = deployments.get(rn.nextInt(deployments.size()));
			if (bestDeployment == null || bestDeployment.NPV < d.NPV)
				bestDeployment = d;
		}
		return bestDeployment;
	}

	// Crossover
	public void crossover(List<Deployment> nextGeneration) {
		Random rn = new Random();
		if (rn.nextDouble() < crossOverProb) {
			Deployment d1 = tournamentSelection();
			Deployment d2 = tournamentSelection();
			d1.crossover(d2);
			nextGeneration.add(d1);
			nextGeneration.add(d2);
		}

	}

	// Mutation
	public void mutation(List<Deployment> nextGeneration) {
		Random rn = new Random();
		if (rn.nextDouble() < mutationProb) {
			Deployment newDeployment = tournamentSelection();
			newDeployment.mutate();
			nextGeneration.add(newDeployment);
		}
	}

	// // Selection
	// void selection() {
	// calculateFitness();
	// fittest = getFittest();
	// secondFittest = getSecondFittest();
	// }
	//
	// // Get fittest offspring
	// Deployment getFittestOffspring() {
	// if (fittest.NPV > secondFittest.NPV) {
	// return fittest;
	// }
	// return secondFittest;
	// }
	//
	// void addFittestOffspring() {
	// // Update fitness values of offspring
	// fittest.calculateNetPresentValue();
	// secondFittest.calculateNetPresentValue();
	//
	// // Get index of least fit individual
	// int leastFittestIndex = getLeastFittestIndex();
	//
	// // Replace least fittest individual from most fittest offspring
	// deployments[leastFittestIndex] = getFittestOffspring();
	// }

	// Print
	public void print(int generationNo, double fitness) {
		System.out.println("Generation " + generationNo + ": Best so far: " + fitness);
	}
}