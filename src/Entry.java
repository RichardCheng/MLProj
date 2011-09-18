
public class Entry<LabelType, FeatureType> {

	public LabelType label;
	public FeatureType[] features; 
	public double predictedLabel; 

	
	public Entry (LabelType label, FeatureType[] features){
		this.label = label;
		this.features = features; 
		this.predictedLabel = Double.MAX_VALUE; 
	}
	
	
	public static void check (Entry e1, Entry e2) throws Exception{
		if (e1.features.length != e2.features.length)
			throw new Exception("unexpected feature size"); 
	}

	
	
}