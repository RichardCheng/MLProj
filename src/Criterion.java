import java.util.ArrayList;

public interface Criterion {
    // Return a double represents how good the split is. 
    // Higher the value is, better the split is.
	public double calculateSplitPerf(ArrayList<Entry> lchild,
			ArrayList<Entry> rchild, ArrayList<Entry> all);
}
