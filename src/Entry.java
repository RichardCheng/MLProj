
public class Entry {

	public int label;
	public Integer[] features; 
	
	public Entry (int label, Integer[] features){
		this.label = label;
		this.features = features; 
	}
	
	
	public static void sanityCheck (Entry e1, Entry e2) throws Exception{
		if (e1.features.length != e2.features.length)
			throw new Exception("unexpected feature size"); 
	}
	
}