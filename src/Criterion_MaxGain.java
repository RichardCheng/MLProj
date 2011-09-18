import java.util.ArrayList;
import java.util.HashMap;

public class Criterion_MaxGain implements Criterion {

	private double calculateEntropy(ArrayList<Entry> entries) {
		double entropy = 0;
		HashMap<Integer, Integer> test = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < entries.size(); ++i) {
			if (test.containsKey(entries.get(i).label)) {
				test.put(entries.get(i).label, test.get((entries.get(i).label)) + 1);
			}
		}
		
		for (int key : test.keySet()) {
			entropy += (double)(test.get(key))/((double)entries.size());
		}
		
		return entropy;
	}
	
	@Override
	public double CalculateSplitPerf(ArrayList<Entry> lchild,
			ArrayList<Entry> rchild, ArrayList<Entry> all) {
		
		double entropyBefore = calculateEntropy(all);
		double entropyAfter = calculateEntropy(lchild) + calculateEntropy(rchild);
		
		return entropyBefore - entropyAfter;
	}

}
