
public class Entry {

	public int label;
	public Integer[] features; 
	public int predictedLabel; 

	
	public Entry (int label, Integer[] features){
		this.label = label;
		this.features = features; 
		this.predictedLabel = Integer.MAX_VALUE; 
	}
	
	
	public static void check (Entry e1, Entry e2) throws Exception{
		if (e1.features.length != e2.features.length)
			throw new Exception("unexpected feature size"); 
	}
	
}