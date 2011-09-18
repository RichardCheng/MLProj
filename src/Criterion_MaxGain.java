import java.util.ArrayList;

public class Criterion_MaxGain implements Criterion {

	private double calculateEntropy(ArrayList<Entry> entries) {
		int countZero = 0;
		int countOne = 0;
		
		for (Entry e : entries) {
			if (e.label == 0)
				++countZero;
			else
				++countOne;
		}
		
		return (double)(countZero)/((double)entries.size()) 
				+ (double)(countOne)/((double)entries.size());
	}
	
	@Override
	public double CalculateSplitPerf(ArrayList<Entry> lchild,
			ArrayList<Entry> rchild, ArrayList<Entry> all) {
		
		double entropyBefore = calculateEntropy(all);
		double entropyAfter = calculateEntropy(lchild) + calculateEntropy(rchild);
		
		return entropyBefore - entropyAfter;
	}

}
