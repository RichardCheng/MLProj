public class Main {
	
	/*
	private static void simulate (String trainingFile, String testFile, String validationFile, int stoppingParam) throws Exception{
		ArrayList<Entry> trainingData = DataReader.read(trainingFile);
		EntryPool trainer = new EntryPool(trainingData); 
		
		ArrayList<Entry> testData = DataReader.read(testFile);
		EntryPool trainee = new EntryPool(testData);
		
		ArrayList<Entry> validationData = DataReader.read(validationFile);
		EntryPool validator = new EntryPool(validationData);
		
		for (Entry e : trainee.pool){
			//e.predictedLabel = trainer.pred_weighted(e, k, weightType);
		}
		
		
		//trainee.genPredReport("f=" + trainingFile.substring(trainingFile.lastIndexOf('.')) + "-k=" + k + "-w=" + ((weightType==0)?("false"):("true (type " + weightType + ")")) + "-n=" + normalized); 
		
		System.out.println("The following parameters:" +
				"\n\tTraining file: " + trainingFile + 
				"\n\tTest file: " + testFile +
				"\n\tValidation file: " + validationFile +
				"\n\tStopping Parameter: " + stoppingParam); 
		
	}
	*/
	
    public static void CrossValidate() {
    
    }

	public static void main(String[] args){
		
		try {
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
