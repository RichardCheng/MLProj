import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataReader {

	public static ArrayList<Entry> read(String filepath) throws Exception{

		BufferedReader input = new BufferedReader(new FileReader(filepath));

		String line = null;
		String featureStr, labelStr; 
		int index; 
		Integer label; 
		ArrayList<Integer> features; 
		
		ArrayList<Entry> datalst = new ArrayList<Entry>(); 

		while ((line = input.readLine()) != null) {
			line = line.substring(0, line.indexOf('#')).trim();
			
			//find label
			index = line.indexOf(' '); 
			labelStr = line.substring(0, index); 
			if (labelStr.length() == 0)
				throw new Exception ("invalid label");
			label = Integer.parseInt(labelStr); 
			
			line = line.substring(index+1).trim() + " "; 
			
			//find features
			features = new ArrayList<Integer>(9); 
			while (true){
				index = line.indexOf(' '); 
				if (index == -1)
					break; 
				
				featureStr = line.substring(0, index);
				line = line.substring(index+1); 
				
				index = featureStr.indexOf(':'); 
				if (index == -1)
					throw new Exception ("invalid feature: missing ':'");;
				featureStr = featureStr.substring(index+1); 
				
				if (featureStr.length() == 0)
					throw new Exception ("invalid feature");
				features.add(Integer.parseInt(featureStr)); 
			}
			
			Integer[] temparray = new Integer[features.size()];
			features.toArray(temparray);
			
			datalst.add(new Entry(label, temparray));  
		}

		input.close();
		
		return datalst; 

	}
}