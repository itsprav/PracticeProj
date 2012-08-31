package com.practice.algos.dynamicProgramming.coinchange;


/**
 * 
 * @author Vijay Kumar : email kumar.vijay@gmail.com
 * Distributed under Apache 2.0 License
 * 
 * Programatically solves the Coin Change problem. 
 * The program computes the following
 * 1. All possible solutions 
 * 2. The optimal solution.
 * 
 * References:
 * http://condor.depaul.edu/~rjohnson/algorithm/coins.pdf
 * http://www.algorithmist.com/index.php/Coin_Change
 * 
 */
public class CoinChangeAlgo {

	/**
	 * Find all possible solutions recursively
	 * @param tsoln - The current solution string
	 * @param startIx - The start index in the denomination array.
	 * @param remainingTarget - The remaining target amount to be satisfied.
	 * @param answer - The final answer object.
	 */
	private void findAllCombinationsRecursive(String tsoln,
			int startIx,
			int remainingTarget,
			CoinChangeAnswer answer) {
		for(int i=startIx; i<answer.denoms.length ;i++) {
			int temp = remainingTarget - answer.denoms[i];
			String tempSoln = tsoln + "" + answer.denoms[i]+ ",";
			if(temp < 0) {
				break;
			}
			if(temp == 0) {
				// reached the answer hence quit from the loop
				answer.allPossibleChanges.add(tempSoln);
				break;
			} else {
				// target not reached, try the solution recursively with the
				// current denomination as the start point.
				findAllCombinationsRecursive(tempSoln, i, temp, answer);
			}
		}
	}
	
	/**
	 * Find all possible solutions for a target amount given the denominations
	 * @param target - The target amount, say 12
	 * @param denoms - The denominations which must be in an order and must not contain duplicates
	 * @return The Answer object which contains the list of all possible solutions.
	 */
	public CoinChangeAnswer findAllPossibleCombinations(int target, int[] denoms) {
		CoinChangeAnswer soln = new CoinChangeAnswer(target,denoms);
		String tempSoln = new String();
		findAllCombinationsRecursive(tempSoln, 0, target, soln);
		return soln;
	}
	
	/**
	 * Find the optimal solution for a given target value and the set of denomiations
	 * @param target
	 * @param denoms
	 * @return
	 */
	public CoinChangeAnswer findOptimalChange(int target, int[] denoms) {
		CoinChangeAnswer soln = new CoinChangeAnswer(target,denoms);
		StringBuilder sb = new StringBuilder();
		
		// initialize the solution structure
		for(int i=0; i<soln.OPT[0].length ; i++) {
			soln.OPT[0][i] = i;
			soln.optimalChange[0][i] = sb.toString();
			sb.append(denoms[0]+" ");
		}

		// Read through the following for more details on the explanation
		// of the algorithm.
		// http://condor.depaul.edu/~rjohnson/algorithm/coins.pdf
		for(int i=1 ; i<denoms.length ; i++) {
			for(int j=0; j<target+1 ; j++) {
				int value = j;
				int targetWithPrevDenomiation = soln.OPT[i-1][j];
				int ix = (value) - denoms[i];
				if( ix>=0 && (denoms[i] <= value )) {
					int x2 = denoms[i] + soln.OPT[i][ix];
					if(x2 <= target && (1+soln.OPT[i][ix] < targetWithPrevDenomiation)) {
						String temp = soln.optimalChange[i][ix] + denoms[i] + " ";
						soln.optimalChange[i][j] = temp;
						soln.OPT[i][j] = 1 + soln.OPT[i][ix];
					} else {
						soln.optimalChange[i][j] = soln.optimalChange[i-1][j]+ " ";
						soln.OPT[i][j] = targetWithPrevDenomiation;
					}
				} else {
					soln.optimalChange[i][j] = soln.optimalChange[i-1][j];
					soln.OPT[i][j] = targetWithPrevDenomiation;
				}
			}
		}
		return soln;
	}
	
	public static void main(String[] args) {
		CoinChangeAlgo ch = new CoinChangeAlgo();
		
		// target is 15 and change is 1,6,7
		CoinChangeAnswer soln = ch.findAllPossibleCombinations(15, new int[] {1,6,7});
		soln.printAllPossibleCombos();
		

		// example 2
		soln = ch.findAllPossibleCombinations(4, new int[] {1,2,3});
		soln.printAllPossibleCombos();
		
		// example 3
		soln = ch.findAllPossibleCombinations(15, new int[] {1,3,5,10});
		soln.printAllPossibleCombos();
		
		
		// optimal solution for target=15
		//soln = ch.findOptimalChange(15, new int[] {1,5,3,10});
		soln = ch.findOptimalChange(4, new int[] {1,2,3});
		System.out.println(soln.getOptimalChange());
	}
}