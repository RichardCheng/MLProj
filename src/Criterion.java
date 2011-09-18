import java.util.ArrayList;

public interface Criterion<LabelType> {
    // Return a double represents how good the split is. 
    // Higher the value is, better the split is.
	public double CalculateSplitPerf(ArrayList<Entry<LabelType, ?>> lchild,
			ArrayList<Entry<LabelType, ?>> rchild, ArrayList<Entry<LabelType, ?>> all);
}
