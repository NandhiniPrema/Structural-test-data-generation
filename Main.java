package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.opt4j.core.Individual;
import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.Value;
import org.opt4j.core.common.random.RandomMersenneTwister;
import org.opt4j.core.optimizer.Archive;
import org.opt4j.core.start.Opt4JModule;
import org.opt4j.core.start.Opt4JTask;
import org.opt4j.optimizers.ea.EvolutionaryAlgorithmModule;
import org.opt4j.optimizers.rs.RandomSearchModule;

public class Main {
	static enum Algorithm {
		RANDOM, EA
	};

	public static void main(String[] arg) {
		TestMinCreator creator = new TestMinCreator();
		TestMinDecoder decoder = new TestMinDecoder();
		TestMinEvaluator evaluator = new TestMinEvaluator();
		System.out.println(" EVOLUTIONARY ALGORITHM: ");
		System.out.println();
		for (int i = 0; i < 100; i++) {
			Objectives objectives = evaluator.evaluate(decoder.decode(creator.create()));
			System.out.println(objectives.toString());
		}
		String adding[] = new String[100];
		EvolutionaryAlgorithmModule eaAlgorithm = new EvolutionaryAlgorithmModule();
		eaAlgorithm.setGenerations(10);
		eaAlgorithm.setAlpha(100);
		eaAlgorithm.setMu(20);
		eaAlgorithm.setCrossoverRate(70);
		eaAlgorithm.setLambda(20);

		RandomSearchModule randomAlgorithm = new RandomSearchModule();
		randomAlgorithm.setBatchsize(1);
		randomAlgorithm.setIterations(1);
		TestMinModule testMinModule = new TestMinModule();

		Algorithm algo = Algorithm.EA;
		Opt4JModule optimiser = null;
		switch (algo) {
		case EA:
			// System.out.println("Evolutionary algorithm Search:");
			optimiser = eaAlgorithm;
			// break;//
		case RANDOM:

			// System.out.println("Random Search:");
			optimiser = randomAlgorithm;
			break;

		default:
			break;
		}

		System.out.println();
		System.out.println("RANDOM SEARCH: ");
		System.out.println();
		for (int i = 0; i < 100; i++) {
			Opt4JTask task = new Opt4JTask(false);
			task.init(optimiser, testMinModule);
			double sum = 0;
			try {
				// System.out.println("Starting algorithm " + algo.name());
				task.execute();
				ArrayList a = new ArrayList();
				Archive archive = task.getInstance(Archive.class);
				for (Individual individual : archive) {
					Objectives objectives = individual.getObjectives();
					for (Entry<Objective, Value<?>> key : objectives) {
						// String var=key.toString();
						// a.add(var);
						System.out.print(key.toString() + " ");
						Value<?> fitness = key.getValue();
						// System.out.println(fitness);
						double result = fitness.getDouble();
						System.out.println();
						// System.out.println("HIT_AND_MISS: ");
						if (result == 0.0) {
							// System.out.println("hit " + result);
						} else {
							// System.out.println("miss " + result);
						}
					}
					System.out.println();

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				task.close();
			}

			// System.out.println("Finished algorithm " + algo.name());
		}
	}
}
