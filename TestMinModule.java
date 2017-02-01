package assignment3;


import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

public class TestMinModule extends ProblemModule 
{
	@Constant(value="population")
	private
	int population;
	
	protected void config()
	{
		bindProblem(TestMinCreator.class, TestMinDecoder.class,TestMinEvaluator.class);
    }

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}
