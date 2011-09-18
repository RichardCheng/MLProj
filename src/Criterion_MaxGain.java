import java.util.ArrayList;
import java.util.HashMap;

public class Criterion_MaxGain<LabelType> implements Criterion<LabelType> {

	private double calculateEntropy(ArrayList<Entry<LabelType, ?>> entries) {
		double entropy = 0;
		HashMap<LabelType, Integer> test = new HashMap<LabelType, Integer>();
		
		for (int i = 0; i < entries.size(); ++i) {
			if (test.containsKey(entries.get(i).label)) {
				test.put(entries.get(i).label, test.get((entries.get(i).label)) + 1);
			}
		}
		
		for (LabelType key : test.keySet()) {
			entropy += (double)(test.get(key))/((double)entries.size());
		}
		
		return entropy;
	}
	
	@Override
	public double CalculateSplitPerf(ArrayList<Entry<LabelType, ?>> lchild,
			ArrayList<Entry<LabelType, ?>> rchild, ArrayList<Entry<LabelType, ?>> all) {
		
		double entropyBefore = calculateEntropy(all);
		double entropyAfter = calculateEntropy(lchild) + calculateEntropy(rchild);
		
		return entropyBefore - entropyAfter;
	}

}
