package assignment3;

import org.opt4j.core.problem.Decoder;

import java.util.ArrayList;
import java.util.List;

import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.genotype.DoubleGenotype;
import org.opt4j.core.genotype.IntegerGenotype;

public class TestMinDecoder implements Decoder<DoubleGenotype, Finder> {
	@Override
	public Finder decode(DoubleGenotype genotype) {
		return new Finder(genotype.get(0), genotype.get(1), genotype.get(2));
	}
}
