package assignment3;


import java.util.Random;

import org.opt4j.core.problem.Creator;
import org.opt4j.core.start.Constant;
import org.opt4j.core.genotype.DoubleGenotype;
import org.opt4j.core.genotype.IntegerGenotype;

import com.google.inject.Inject;

public class TestMinCreator implements Creator<DoubleGenotype>{
Random random = new Random();
private int population = 20;

@Inject
public void TestMincreator(@Constant(value="population")int population)
{
	this.population=population;
}

public DoubleGenotype create() {
DoubleGenotype genotype = new DoubleGenotype(-20,20);
genotype.init(random, 3); // Better to use injection
return genotype;
}
}
