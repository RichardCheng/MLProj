import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args){
		try {
			// OK let's do this.
			
			///////////////////////////////////////////////////////////////////
			//// Data used across various parts.
			ArrayList<Entry> bcan_test = DataReader.read("bcan/bcan.test");
			ArrayList<Entry> bcan_train = DataReader.read("bcan/bcan.train");
			ArrayList<Entry> bcan_train30 = DataReader.read("bcan/bcan.train30");
			ArrayList<Entry> bcan_train90 = DataReader.read("bcan/bcan.train90");
			ArrayList<Entry> bcan_validate = DataReader.read("bcan/bcan.validate");
			int[] stoppingParamList = new int[] {1,5,9,17,25,37,43,49};
			
			///////////////////////////////////////////////////////////////////
			
			// Part a)
			System.out.printf("\n\nPart a)\n");
			NodeFactory.changeCriterion(new Criterion_MaxGain());
			
			for (int i : stoppingParamList) {
				NodeFactory.changeStoppingParam(i);
				
				Tree t1 = new Tree();
				// Grow tree using bcan_train90
				t1.growTree(bcan_train90);
				
				// Print the testing, validate, and training errors
				System.out.printf("For stopping param = %d, Testing Error " +
						"= %d, Validate Error = %d, Training Error = %d\n", 
						i, t1.getError(bcan_test), t1.getError(bcan_validate),
						t1.getError(bcan_train90));
			}
			
			///////////////////////////////////////////////////////////////////
			// Part b)
			System.out.printf("\n\nPart b)\n");
			NodeFactory.changeCriterion(new Criterion_MinError());
			
			for (int i : stoppingParamList) {
				NodeFactory.changeStoppingParam(i);
				
				Tree t2 = new Tree();
				// Grow tree using bcan_train90
				t2.growTree(bcan_train90);
				
				// Print the testing, validate, and training errors
				System.out.printf("For stopping param = %d, Testing Error " +
						"= %d, Validate Error = %d, Training Error = %d\n", 
						i, t2.getError(bcan_test), t2.getError(bcan_validate),
						t2.getError(bcan_train90));
			}
			
			///////////////////////////////////////////////////////////////////
			// Part c)
			System.out.printf("\n\nPart c)\n");
			NodeFactory.changeCriterion(new Criterion_MaxGain());
			NodeFactory.changeStoppingParam(1);
			// Grow tree using bcan_train90
			Tree t3 = new Tree();
			t3.growTree(bcan_train90);
			
			// Prune the tree using bcan_validate
			t3.Prune(bcan_validate);
			
			// Print the test errors, and total number of nodes
			System.out.printf("The Test Error = %d\nTotal number of nodes = %d", 
					t3.getError(bcan_test), t3.nodeCount());
			
			///////////////////////////////////////////////////////////////////
			// Part d)
			// Please refer to the function.
			System.out.printf("\n\nPart d)\n");
			crossValidation(bcan_train, bcan_test, stoppingParamList);
			
			///////////////////////////////////////////////////////////////////
			// Part e)
			System.out.printf("\n\nPart e)\n");
			NodeFactory.changeCriterion(new Criterion_MaxGain());
			
			for (int i : stoppingParamList) {
				NodeFactory.changeStoppingParam(i);
				
				Tree t5 = new Tree();
				// Grow tree using bcan_train30
				t5.growTree(bcan_train30);
				
				// Print the testing, validate, and training errors
				System.out.printf("For stopping param = %d, Testing Error " +
						"= %d, Validate Error = %d, Training Error = %d\n", 
						i, t5.getError(bcan_test), t5.getError(bcan_validate),
						t5.getError(bcan_train30));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	private static void crossValidation(ArrayList<Entry> bcan_train, 
			ArrayList<Entry> bcan_test, int[] stoppingParamList) throws Exception {
		
		NodeFactory.changeCriterion(new Criterion_MaxGain());
		// Make a copy
		ArrayList<Entry> bcan_train_cp = new ArrayList<Entry>(bcan_train);
		
		// Knuth Shuffle. 
		for (int i = 0; i < bcan_train_cp.size(); ++i) {
			int loc = MathUtilities.genRandomIntRange(i, bcan_train_cp.size());
			if (loc != i) {
				Entry e = bcan_train_cp.get(i);
				bcan_train_cp.set(i, bcan_train_cp.get(loc));
				bcan_train_cp.set(loc, e);
			}
		}
		
		// Generate errors for all cases
		int[][] bestStoppingParamList = new int[10][stoppingParamList.length];
		for (int i = 0; i < 10; ++i) {
			ArrayList<Entry> training_p4 = new ArrayList<Entry>();
			ArrayList<Entry> validating_p4 = new ArrayList<Entry>();
			
			// Build training and validating set with train
			for (int j = 0; j < i*55; ++j) {
				if (j < i*55 || j >= (i+1)*55)
					training_p4.add(bcan_train.get(j));
				else
					validating_p4.add(bcan_train.get(j));
			}
			
			for (int j= 0; j < stoppingParamList.length; ++j) {
				NodeFactory.changeStoppingParam(stoppingParamList[j]);
				
				Tree t4 = new Tree();
				// Grow tree using training_p4
				t4.growTree(training_p4);
				
				// Validate the tree using validating_p4
				// Record the error at bestStoppingParamList[i][j]
				bestStoppingParamList[i][j] = t4.getError(validating_p4);
			}
		}
		
		int bestStoppingParam = -1;
		int bestError = Integer.MAX_VALUE;
		for (int j = 0; j < stoppingParamList.length; ++j) {
			int currError = 0;
			for (int i = 0; i < 10; ++i) {
				currError += bestStoppingParamList[i][j];
			}
			
			if (currError < bestError) {
				bestError = currError;
				bestStoppingParam = stoppingParamList[j];
			}
		}
		
		// Report bestStoppingParam as best stopping param.
		System.out.printf("The best stopping parameter = %d\n", bestStoppingParam);
		
		NodeFactory.changeStoppingParam(bestStoppingParam);
		System.out.println("bestSP: " + bestStoppingParam);
		Tree t4best = new Tree();
		// Grow tree using bcan_train
		t4best.growTree(bcan_train);
		
		// Print the testing errors
		System.out.printf("The Test Error = %d\n", 
				t4best.getError(bcan_test));
	}
}
