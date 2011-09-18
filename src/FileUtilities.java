import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author hank.duan
 * 
 */
public class Utilities {

	/**
	 * write (String data, String fileloc) writes data to a file with its
	 * location defined in fileloc
	 * 
	 * @param data
	 *            : data to be written
	 * @param fileloc
	 *            : location of file to be written to
	 */
	public static void write(String data, String fileloc) {
		// Create directory folders (and possibly parent folders) if the
		// directory does not exist
		File f = new File(fileloc + "/..");
		try {
			f.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Create file
			FileWriter fstream = new FileWriter(fileloc);
			BufferedWriter out = new BufferedWriter(fstream);

			// Write
			out.write(data);
			// Close the output stream
			out.close();
		} catch (Exception ex) {// Catch exception if any
			ex.printStackTrace();
		}
	}
	
	public static void append(String data, String fileloc) {
		// Create directory folders (and possibly parent folders) if the
		// directory does not exist
		File f = new File(fileloc + "/..");
		try {
			f.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Create file
			FileWriter fstream = new FileWriter(fileloc, true);
			BufferedWriter out = new BufferedWriter(fstream);

			// Write
			out.write(data + "\r\n");
			// Close the output stream
			out.close();
		} catch (Exception ex) {// Catch exception if any
			ex.printStackTrace();
		}
	}

	public static String read(String fileloc) {
		
		String contents = "";

		try {
			BufferedReader input = new BufferedReader(new FileReader(fileloc));

			String line = null;

			while ((line = input.readLine()) != null) {
				contents += line;
				contents += System.getProperty("line.separator");
			}

			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return contents;
	}
	
	//find euclidean distance between two entries
	public static double euc_dist (Entry e1, Entry e2) throws Exception{
		Entry.check(e1, e2); 
		double sum = 0; 
		for (int i = 0; i < e1.features.length; i++){
			sum += (Math.pow(e1.features[i] - e2.features[i], 2.0)); 
		}
	
		return Math.sqrt(sum); 
	}
	
	public static double get_average (ArrayList<Double> lst){
		double sum = 0; 
		for (Double d : lst){
			sum += d;
		}
		return sum/lst.size(); 
		
	}
	
	public static double get_stddev (ArrayList<Double> lst){
		double sum = 0;
		double average = get_average(lst); 
		for (Double d : lst){
			sum += (d - average) * (d-average);
		}
		return Math.sqrt(sum/lst.size()); 
	}
	
	// Compares two doubles to see if they're equal
	boolean DoubleEquals (double A, double B){
	    if (A == B)
	        return true;
	    
		double maxRelativeError = 0.0001; 
	    double relativeError;
	    if (B > A)
	        relativeError = (B - A) / B;
	    else
	        relativeError = (A - B) / A;
	    return relativeError <= maxRelativeError;
	}
}