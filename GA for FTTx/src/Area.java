import java.util.Map;

public class Area {

	int population;
	int populationAdopted;
	int populationUnadopted;
	double adoptionRate;
	int MRPValue; // maximum roll-out period

	public Area(int population, double adoptionRate) {
		this.population = population;
		this.populationAdopted = 0;
		this.populationUnadopted = population;
		this.adoptionRate = adoptionRate;
	}

	public Area(Area area) {
		this.population = area.population;
		this.populationAdopted = 0;
		this.populationUnadopted = area.population;
		this.adoptionRate = area.adoptionRate;
	}
	
	public void reset() {
		this.populationAdopted = 0;
		this.populationUnadopted = this.population;
	}
	
	public void calculatePresentValue(Map<Integer, Double> annualCashFlows) {
		reset();
		if (MRPValue == 0)
			return;
		else
			calculatePresentValue(1, annualCashFlows);
	}

	public void calculatePresentValue(int year, Map<Integer, Double> annualCashFlows) {
		if (year > Inputs.studyPeriod)
			return;
		else if (year < MRPValue)
			calculatePresentValue(++year, annualCashFlows);
		else if (year == MRPValue) {
			double cost = Inputs.CAPEX;
			double income = 0;
			double cashFlow = income - cost;
			annualCashFlows.put(year, annualCashFlows.get(year) + cashFlow);
			calculatePresentValue(++year, annualCashFlows);
		} else {
			double cost = Inputs.OPEX;
			int newPopulationAdopts = (int) (populationUnadopted * adoptionRate);
			populationAdopted += newPopulationAdopts;
			populationUnadopted -= newPopulationAdopts;
			double income = populationAdopted * Inputs.annualRental;
			double cashFlow = income - cost;
			annualCashFlows.put(year, annualCashFlows.get(year) + cashFlow);
			calculatePresentValue(++year, annualCashFlows);
		}
	}
}
