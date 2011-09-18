import java.util.ArrayList;

public class Criterion_MinError implements Criterion {

	@Override
	public double CalculateSplitPerf(ArrayList<Entry> lchild,
			ArrayList<Entry> rchild, ArrayList<Entry> all) {
		
		int typel = Node.CalculateType(lchild);
		int errorCount = 0;
		for (Entry e : lchild) {
			if (e.label != typel) 
				errorCount++;
		}
		
		int typer = Node.CalculateType(rchild);
		for (Entry e : rchild) {
			if (e.label != typer) {
				errorCount++;
			}
		}
		
		return -errorCount;
	}

}
