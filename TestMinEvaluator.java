package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import com.google.inject.Inject;

public class TestMinEvaluator implements Evaluator<Finder> {

	Example quadRoots;

	private static int K = 4;

	@Inject
	public TestMinEvaluator() {
		quadRoots = new Example();

	}

	@Override
	public Objectives evaluate(Finder finder) {
		Objectives objectives = new Objectives();
		List<Double> results = new ArrayList<Double>();

		double fitnessD = 5, fitnessB = 5, fitnessO = 5;
		// for (Finder holder : finder)
		// {
		Map<String, Object> map = quadRoots.findQuadraticRoots(finder);
		String target = map.get("target").toString();
		double branchDistance1 = calculateBranchDistanceFortarget1(target, finder);
		double approachLevel1 = calculateApproachLevelFortarget1();
		double branchDistance2 = calculateBranchDistanceFortarget2(target, finder);
		double approachLevel2 = calculateApproachLevelFortarget2(target);
		double branchDistance3 = calculateBranchDistanceFortarget3(target, finder);
		double approachLevel3 = calculateApproachLevelFortarget3(target);
		for (int i = 0; i < 100; i++) {
			double fitness1 = calculateFitnessFunction(approachLevel1, branchDistance1);
			double fitness2 = calculateFitnessFunction(approachLevel2, branchDistance2);
			double fitness3 = calculateFitnessFunction(approachLevel3, branchDistance3);
			// System.out.println("Fitness : of target 1 " + fitness1);
			// System.out.println("Fitness : of target 2 " + fitness2);
			// System.out.println("Fitness : of target 3 " + fitness3);
			results.add(fitness1);
			Double sum1 = new Double(0);
			for (Double i1 : results) {
				sum1 = sum1 + i1;
			}
			// System.out.println("sum of target1: " + sum1);
			results.add(fitness2);
			double sum2 = 0;
			for (Double i1 : results) {
				sum2 = sum2 + i1;
			}
			// System.out.println("sum of target2: " + sum2);
			results.add(fitness3);
			double sum3 = 0;
			for (Double i3 : results) {
				sum3 = sum3 + i3;
			}
		//	System.out.println("sum of target1: " + sum3);
			objectives.add("target1", Sign.MIN, fitness1);
			objectives.add("target2", Sign.MIN, fitness2);
			objectives.add("target3", Sign.MIN, fitness3);
		}
		return objectives;
	}

	// calculate fitness function
	private double calculateFitnessFunction(double approachLevel, double branchDistance) {
		double normalisedBranchDistance = 1 - Math.pow(1.001, (branchDistance * -1));
		double fitness = approachLevel + normalisedBranchDistance;
		return fitness;
	}

	// target1
	// calculate branch distance
	private double calculateBranchDistanceFortarget1(String target, Finder holder) {
		double branchDistance = 0;
		if (!target.equals("target1")) {
			double discriminant = Example.findDiscriminant(holder.getA(), holder.getB(), holder.getC());
			branchDistance = calculateBranchDistance(discriminant, 0);
			// System.out.println(" target1 Hit");
		}
		return branchDistance;
	}

	// calculate approach level
	private double calculateApproachLevelFortarget1() {
		return 0;
	}

	// target2
	private double calculateBranchDistanceFortarget2(String target, Finder holder) {
		double branchDistance = 0;
		if (!target.equals("target2")) {
			branchDistance = calculateBranchDistance(holder.getB(), 0);
			// System.out.println("target2 Hit");
		}
		return branchDistance;
	}

	private double calculateApproachLevelFortarget2(String target) {
		// double approachLevel = 0;
		// if (target.equals("target2")) {
		// approachLevel = 1;
		// } else if (!target.equals("target2")) {
		// approachLevel = 2;
		// }
		// return approachLevel;
		return 0;
	}

	// target3
	private double calculateBranchDistanceFortarget3(String target, Finder holder) {
		double branchDistance = 0;
		if (!target.equals("target3")) {
			branchDistance = calculateBranchDistance(holder.getC(), 0);
			// System.out.println("target2 Hit");
		}
		return branchDistance;
	}

	private double calculateApproachLevelFortarget3(String target) {
		double approachLevel = 0;
		// if (target.equals("target3")) {
		// approachLevel = 2;
		// } else if (target.equals("target2")) {
		// approachLevel = 1;
		// }
		return approachLevel;
	}

	private double calculateBranchDistance(double a, double b) {
		if ((a - b) < 0)
			return 0;
		else
			return (a - b) + K;
	}

}
